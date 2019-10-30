package com.example.nasa_api.model

import com.example.nasa_api.common_classes.NasaData
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.common_classes.NasaRequest
import com.example.nasa_api.common_classes.SearchResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Model {
    private val TAG = "RXTEST_Model"
    private val repo = Repo.getRepo()

    // memory leak ON
    fun getSearchResponse(subscriber: Observer<NasaNode>, request: NasaRequest){
        val tmp = repo.search(request.q, request.center, request.page, request.nasaId)
            .subscribeOn(Schedulers.io())
            .flatMapObservable { response ->
                //                Log.d(TAG, "flatMapObservable 1 ")
                Observable.fromIterable(getNodesFromResult(response)) }
            .flatMap(
                {nasaData ->
                    //                    Log.d(TAG, "flatMap 2 - 1 ")
                    repo.asset(nasaData.nasaId)},
                {nasaData, mediaResult ->
                    //                    Log.d(TAG, "flatMap 2 - 2 ")
                    NasaNode(nasaData, getMediaFromResult(mediaResult))
                }
            )
            .observeOn(AndroidSchedulers.mainThread())
        tmp.subscribe(subscriber)
        //tmp.unsubscribeOn(Schedulers.io())
    }

    private fun getNodesFromResult(searchResult: SearchResponse): List<NasaData>{
        val res = mutableListOf<NasaData>()
        searchResult.collection.items?.forEach {
            it.data?.first()?.let{ nasa_data ->
                res.add(nasa_data)
            }
        }
        return res
    }
    private fun getMediaFromResult(searchResult: SearchResponse): List<String>{
        val res = mutableListOf<String>()
        searchResult.collection.items?.forEach {
            res.add(it.href)
        }
        return res
    }
}