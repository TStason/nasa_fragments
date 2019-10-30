package com.example.nasa_api.ui.main

import android.os.Bundle
import android.util.Log
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.BaseActivity
import com.example.nasa_api.ui.base.ShowDetail

class MainActivity : BaseActivity(), ShowDetail {
    override val TAG = "RXTEST_MainActivity"

    override val searchFragmentTag: String = "search"
    override val detailFragmentTag: String = "info"
    override fun showDetailFragment(item: NasaNode) {
        Log.d(TAG, "Start Fragment -> $item")
        // получаем фрагмент
        // либо из менеджера -- крайне сомнительно
        // либо билдим фрагмент
        // настройка билдером?
        // возможно стоит разбить DetailFragment на 2(3) класса
        // с медиаплеером (видео, аудио) и без (просто картинка)
        val fragment1 = DetailFragmentFactory.getDetailFragment(item)
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_holder, fragment1, detailFragmentTag)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate -> $savedInstanceState")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = supportFragmentManager.findFragmentByTag(searchFragmentTag)
            Log.d(TAG, "onCreate fragment -> $fragment")
            fragment?.let{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, it)
                    .commit()
            } ?:
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, SearchFragment(), searchFragmentTag)
                .commit()
        }
    }
}
