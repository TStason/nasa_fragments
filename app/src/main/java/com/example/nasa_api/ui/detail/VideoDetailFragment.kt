package com.example.nasa_api.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DetailFragmentServiceOwner
import kotlinx.android.synthetic.main.detail_video_fragment.*

class VideoDetailFragment(item: NasaNode): DetailFragmentServiceOwner() {
    override val TAG = "RXTEST_VideoDetailFragment"
    override var presenter: DetailPresenterServiceOwner? = DetailPresenterServiceOwner(item)
    //----------------//
    private val surfaceHolderCallback = object : SurfaceHolder.Callback{
        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            Log.d(TAG, "surface.holder: surfaceChanged -> $holder")
        }
        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            Log.d(TAG, "surface.holder: surfaceDestroyed -> $holder")
            service?.mediaPlayer?.pause()
        }
        override fun surfaceCreated(holder: SurfaceHolder?) {
            Log.d(TAG, "surface.holder: surfaceCreated -> $holder")
            service?.mediaPlayer?.setDisplay(holder)
        }
    }
    //----------------//
    private var mediaController: MediaController? = null
    //
    override fun setMedia(url: String?) {
        Log.d(TAG, "set video -> $url")
        service?.let{
            it.setUrl(url)
            it.mediaPlayer.setDisplay(surfaceView.holder)
            mediaController?.setMediaPlayer(it)
            mediaController?.setAnchorView(surfaceView)
            surfaceView.setOnClickListener {
                mediaController?.show()
            }
            it.prepareAsync()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        mediaController = MediaController(context)
        //------------------------//
        surfaceView.holder.addCallback(surfaceHolderCallback)
        //------------------------//
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView(){
        surfaceView?.setOnClickListener(null)
        surfaceView.holder.removeCallback(surfaceHolderCallback)
        mediaController = null
        super.onDestroyView()
    }
}