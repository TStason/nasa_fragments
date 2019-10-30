package com.example.nasa_api.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DetailFragmentServiceOwner

class AudioDetailFragment(item: NasaNode): DetailFragmentServiceOwner() {
    override val TAG = "RXTEST_VideoDetailFragment"
    override var presenter: DetailPresenterServiceOwner? = DetailPresenterServiceOwner(item)

    override fun setMedia(url: String?) {

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }
}