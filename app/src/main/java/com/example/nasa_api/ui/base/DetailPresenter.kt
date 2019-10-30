package com.example.nasa_api.ui.base

import com.example.nasa_api.common_classes.MediaType
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.detail.IDetailFragment

abstract class DetailPresenter<T: IDetailFragment>(private val item: NasaNode): BasePresenter<T>(){
    open fun onActivityCreated(){
        getView()?.setUI(item)
    }
    open fun onDestroy(){
        detachView()
    }

    private fun getMediaType() = item.nasaData.mediaType

    fun getItem() = item

    protected fun getMediaUrl(): String?{
        var res: String?
        when (getMediaType()){
            MediaType.VIDEO -> res = item.mediaLinks.find { it.endsWith("mobile.mp4", true) }
            MediaType.AUDIO -> {
                res = item.mediaLinks.find { it.endsWith(".mp3") }
                if (res == null)
                    res = item.mediaLinks.find { it.endsWith(".m4a") }
                if (res == null)
                    res = item.mediaLinks.find { it.endsWith(".wav") }
            }
            MediaType.IMAGE -> res = item.mediaLinks.find { it.contains("medium") }
        }
        return res
    }
}