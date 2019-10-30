package com.example.nasa_api.ui.main

import android.util.Log
import com.example.nasa_api.model.Model
import com.example.nasa_api.common_classes.NasaRequest
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.BasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class SearchPresenter<T: ISearchFragment>: BasePresenter<T>(), ISearchPresenter<T> {
    private val TAG = "RXTEST_Presenter"
    private val model = Model()

    private val mutableRes = mutableListOf<NasaNode>()
    private var isUpdated = false

    val ALPHA_OBSERVER = object : Observer<NasaNode> {
        override fun onComplete() {
            Log.d(TAG, "onComplete ${Thread.currentThread()}")
            getView()?.let{
                it.updateRecycler(mutableRes)
                it.stopDisplayRefresh()
            }
            isUpdated = false
            mutableRes.clear()
        }
        override fun onNext(t: NasaNode) {
            //Log.d(TAG, "onNext: $t -- ${Thread.currentThread()}")
            mutableRes.add(t)
            if (mutableRes.count() >= 10){
                getView()?.let{
                    it.updateRecycler(mutableRes)
                }
                mutableRes.clear()
            }
        }
        override fun onSubscribe(d: Disposable) {
            //Log.d(TAG, "onSubscribe ${Thread.currentThread()}")
            ALPHA_DISPOSABLE = d
        }
        override fun onError(e: Throwable) {
            Log.d(TAG, "onError: $e ${Thread.currentThread()}")
        }
    }
    var ALPHA_DISPOSABLE: Disposable? = null

    override fun onButtonClicked(requestString: String?) {
        val request = prepRequest(requestString)
        if (!isUpdated && request != null){
            isUpdated = true
            getView()!!.clearRecycler()
            getView()!!.displayRefresh()
            model.getSearchResponse(ALPHA_OBSERVER, request)
        } else {
            // Already updating
            // Prob re
            getView()?.toast("Already search, or empty request")
        }
    }
    private fun prepRequest(request: String?): NasaRequest?{
        val tmp = request?.trim()
        if (tmp.isNullOrEmpty())
            return null
        return NasaRequest(request)
    }

    fun isUpdated() = isUpdated

    fun onFragmentDestroy(){
        Log.d(TAG, "onFragmentDestroy")
        ALPHA_DISPOSABLE?.dispose()
        isUpdated = false
    }
}