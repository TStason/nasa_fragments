package com.example.nasa_api.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasa_api.R
import com.example.nasa_api.ui.recycler_thing.NAdapter
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.BaseFragment
import com.example.nasa_api.ui.base.ShowDetail
import com.example.nasa_api.ui.recycler_thing.Interaction
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment: BaseFragment(), ISearchFragment {
    override val TAG = "RXTEST_SearchFragment"
    private val dl = mutableListOf<NasaNode>()
    private var ad = NAdapter(dl, object : Interaction{
        override fun onItemSelected(item: NasaNode) {
            startDetailFragment(item)
        }
    })
    //
    private var searchPresenter: SearchPresenter<SearchFragment>? = SearchPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return view ?: inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchPresenter?.attachView(this)
        search_btn?.let{
            it.setOnClickListener {
                searchPresenter?.onButtonClicked(search_edit.text.toString())
            }
        }
        recycler?.let{ recycler ->
            recycler.layoutManager = LinearLayoutManager(this.context)
            recycler.adapter = ad
        }
    }

    override fun onResume() {
        // if presenter in state update
        // show refresher
        searchPresenter?.let{
            if (it.isUpdated())
                displayRefresh()
            super.onResume()
        }
    }
    override fun onStop() {
        if (custom_pb.isAnimated)
            stopDisplayRefresh()
        super.onStop()
    }
    override fun onDestroyView() {
        search_btn?.setOnClickListener(null)
        recycler.adapter = null
        recycler.layoutManager = null
        super.onDestroyView()
    }
    override fun onDestroy() {
        searchPresenter?.onFragmentDestroy()
        searchPresenter?.detachView()
        super.onDestroy()
    }

    private fun startDetailFragment(item: NasaNode){
        Log.d(TAG, "start detail fragment -> Activity call")
        (this.activity as? ShowDetail)?.showDetailFragment(item)
    }

    override fun clearRecycler() {
        dl.clear()
        ad.notifyDataSetChanged()
    }
    override fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    override fun updateRecycler(node: List<NasaNode>) {
        dl.addAll(node)
        ad.notifyDataSetChanged()
    }
    override fun displayRefresh() {
        Log.d(TAG, "displayRefresh -> $custom_pb ---- ${custom_pb?.isAnimated}")
        if(!custom_pb.isAnimated){
            custom_pb.startAnim()
        }
    }
    override fun stopDisplayRefresh() {
        Log.d(TAG, "stopDisplayRefresh -> $custom_pb")
        custom_pb?.stopAnim()
    }
}