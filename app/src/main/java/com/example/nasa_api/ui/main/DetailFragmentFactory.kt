package com.example.nasa_api.ui.main

import androidx.fragment.app.Fragment
import com.example.nasa_api.common_classes.MediaType
import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.detail.AudioDetailFragment
import com.example.nasa_api.ui.detail.ImageDetailFragment
import com.example.nasa_api.ui.detail.VideoDetailFragment

class DetailFragmentFactory {

    companion object{
        fun getDetailFragment(item: NasaNode): Fragment{
            when(item.nasaData.mediaType){
                MediaType.AUDIO -> return AudioDetailFragment(item)
                MediaType.VIDEO -> return VideoDetailFragment(item)
                MediaType.IMAGE -> return ImageDetailFragment(item)
            }
        }
    }
}