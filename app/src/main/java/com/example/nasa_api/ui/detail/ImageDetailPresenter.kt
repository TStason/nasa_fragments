package com.example.nasa_api.ui.detail

import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DetailPresenter

/*
//class VideoDetailPresenter(item: NasaNode): DetailPresenterServiceOwner(item) {
//    override fun onActivityCreated() {
//        getView()?.BindService()
//        super.onActivityCreated()
//    }
//
//    override fun onFragmentDestroy() {
//        getView()?.UnbindService()
//        super.onFragmentDestroy()
//    }
//}
//--------------------------------------------------------------------------------------------------//
//class AudioDetailPresenter(item: NasaNode): DetailPresenterServiceOwner(item) {
//    override fun onActivityCreated() {
//        getView()?.BindService()
//        super.onActivityCreated()
//    }
//
//    override fun onFragmentDestroy() {
//        getView()?.UnbindService()
//        super.onFragmentDestroy()
//    }
//}
//--------------------------------------------------------------------------------------------------//
*/
class ImageDetailPresenter(item: NasaNode): DetailPresenter<ImageDetailFragment>(item) {
    override fun onActivityCreated() {
        getView()?.setMedia(getMediaUrl())
        super.onActivityCreated()
    }
}