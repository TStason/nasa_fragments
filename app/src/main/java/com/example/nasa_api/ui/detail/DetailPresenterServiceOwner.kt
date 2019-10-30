package com.example.nasa_api.ui.detail

import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DetailFragmentServiceOwner
import com.example.nasa_api.ui.base.DetailPresenter

class DetailPresenterServiceOwner(item: NasaNode): DetailPresenter<DetailFragmentServiceOwner>(item){
    fun onMediaPlayerReady(){
        getView()?.setMedia(getMediaUrl())
    }
    override fun onActivityCreated() {
        getView()?.BindService()
        super.onActivityCreated()
    }

    override fun onDestroy() {
        getView()?.UnbindService()
        super.onDestroy()
    }
}