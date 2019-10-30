package com.example.nasa_api.ui.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.nasa_api.ui.detail.DetailPresenterServiceOwner
import com.example.nasa_api.utils.MediaPlayerForegroundService

abstract class DetailFragmentServiceOwner():
    DetailFragment<DetailFragmentServiceOwner, DetailPresenterServiceOwner>(), MediaPlayerForegroundService.ServiceOwner{
    //
    var service: MediaPlayerForegroundService? = null
    private var serviceConnector: ServiceConnection? = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "serviceConnector: onServiceDisconnected")
            //reconnect
        }
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "serviceConnector: onServiceConnected")
            val tmp = service as MediaPlayerForegroundService.VideoBinder
            this@DetailFragmentServiceOwner.service = tmp.getService()
            Toast.makeText(this@DetailFragmentServiceOwner.context, "Service connected", Toast.LENGTH_SHORT).show()
            presenter?.onMediaPlayerReady()
        }

    }

    override fun onDestroy() {
        service = null
        super.onDestroy()
    }
    //
    override fun BindService() {
        Log.d(TAG, "BindService")
        requireActivity().bindService(
            Intent(requireActivity().applicationContext, MediaPlayerForegroundService::class.java),
            serviceConnector,
            Context.BIND_AUTO_CREATE)
    }
    override fun UnbindService() {
        Log.d(TAG, "UnbindService")
        requireActivity().unbindService(serviceConnector)
    }
}