package com.example.nasa_api.utils

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.MediaController
import java.lang.Exception
import java.lang.ref.WeakReference


class MediaPlayerForegroundService: Service(), MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {

    private val TAG = "NASA_MEDIA_PLAYER_SERVICE"

    var mediaPlayer = MediaPlayer()

    private val binder = WeakReference<IBinder>(VideoBinder())
    private var URL: String? = null

    fun setUrl(url: String?){
        Log.d(TAG, "setUrl -> $url")
        URL = url
        try{
            setMediaSource()
        } catch (ex: Exception){
            Log.e(TAG, "Error ${ex.localizedMessage}")
        }
    }
    private fun setMediaSource(){
        mediaPlayer.setDataSource(URL)
    }
    // after prep -> start()
    fun prepareAsync(){
        Log.d(TAG, "prepareAsync -> ${mediaPlayer}")
        URL?.let{
            try{
                mediaPlayer.prepareAsync()
            }
            catch (ex: Exception){
                Log.e(TAG, "Error ${ex.localizedMessage}")
            }
        } ?: Log.e(TAG, "prepareAsync source -> $URL")

    }

    fun setNewPlayer(){
        //mediaPlayer.release()
        //mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this)
    }

    private fun stopMedia(){
        Log.d(TAG, "stopMedia")
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()
        mediaPlayer.stop()
    }

    fun resetMediaPlayer(){
        Log.d(TAG, "resetMediaPlayer")
        stopMedia()
        mediaPlayer.reset()
    }
    // MediaPlayer.OnPreparedListener
    override fun onPrepared(mp: MediaPlayer?) {
        Log.d(TAG, "onPrepared")
    }
    // Service
    override fun onCreate() {
        Log.d(TAG, "onCreate")
        setNewPlayer()
        super.onCreate()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return binder.get()
    }
    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind")
    }
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        mediaPlayer.setOnPreparedListener(null)
        mediaPlayer.release()
        return true
    }
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        //stopMedia()
        //mediaPlayer.setOnPreparedListener(null)
        //mediaPlayer.release()
        super.onDestroy()
    }

    // MediaController.MediaPlayerControl
    override fun isPlaying(): Boolean = mediaPlayer.isPlaying
    override fun canSeekForward(): Boolean = true
    override fun getDuration(): Int = mediaPlayer.duration
    override fun pause() = mediaPlayer.pause()
    override fun getBufferPercentage(): Int = 0
    override fun seekTo(pos: Int) = mediaPlayer.seekTo(pos)
    override fun getCurrentPosition(): Int = mediaPlayer.currentPosition
    override fun canSeekBackward(): Boolean = true
    override fun start() = mediaPlayer.start()
    override fun getAudioSessionId(): Int = mediaPlayer.audioSessionId
    override fun canPause(): Boolean = true

    inner class VideoBinder: Binder(){
        fun getService() = this@MediaPlayerForegroundService
    }
    interface ServiceOwner{
        fun BindService()
        fun UnbindService()
//        fun StartService()
//        fun StopService()
    }
}
