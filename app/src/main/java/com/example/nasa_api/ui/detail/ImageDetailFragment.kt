package com.example.nasa_api.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DetailFragment
import com.example.nasa_api.ui.base.DetailPresenter
import kotlinx.android.synthetic.main.detail_image_fragment.*

class ImageDetailFragment(item: NasaNode):
    DetailFragment<ImageDetailFragment, DetailPresenter<ImageDetailFragment>>() {
    override val TAG = "RXTEST_VideoDetailFragment"
    override var presenter: DetailPresenter<ImageDetailFragment>? = ImageDetailPresenter(item)
    override fun setMedia(url: String?) {
        Log.d(TAG, "set image -> $url")
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_image_fragment, container, false)
    }
}