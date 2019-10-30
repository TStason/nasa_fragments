package com.example.nasa_api.ui.detail

import com.example.nasa_api.common_classes.NasaNode

interface IDetailFragment{
    fun setUI(item: NasaNode)
    fun setMedia(url: String?)
}