package com.example.nasa_api.ui.base

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.detail.IDetailFragment
import kotlin.math.min

abstract class DetailFragment<F: IDetailFragment, T : DetailPresenter<F>>: BaseFragment(),
    IDetailFragment {

    abstract var presenter: T?

    override fun setUI(item: NasaNode){
        Log.d(TAG, "initializeUI -> $item")
        view?.let{
            it.findViewById<TextView>(R.id.detail_title).text = item.nasaData.title
            it.findViewById<TextView>(R.id.detail_media_type).text = item.nasaData.mediaType.name
            it.findViewById<TextView>(R.id.detail_description).text = item.nasaData.description?.substring(0, min(50, item.nasaData.description!!.length))
            it.findViewById<TextView>(R.id.detail_keywords).text = item.nasaData.keywords.toString()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.attachView(this as F)
        presenter?.onActivityCreated()
    }
    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}