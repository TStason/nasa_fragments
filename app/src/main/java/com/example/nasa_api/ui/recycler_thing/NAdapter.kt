package com.example.nasa_api.ui.recycler_thing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.MediaType
import com.example.nasa_api.common_classes.NasaNode

class NAdapter(val dataList: MutableList<NasaNode>,
               private val interaction: Interaction? = null): RecyclerView.Adapter<NasaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when(viewType){
            MediaType.VIDEO.type ->
                return VideoViewHolder(inflater.inflate(R.layout.video_view_holder, parent, false), interaction)
            MediaType.AUDIO.type ->
                return AudioViewHolder(inflater.inflate(R.layout.audio_view_holder, parent, false), interaction)
            MediaType.IMAGE.type ->
                return ImageViewHolder(inflater.inflate(R.layout.image_view_holder, parent, false), interaction)
            else ->
                return ImageViewHolder(inflater.inflate(R.layout.image_view_holder, parent, false), null)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].nasaData.mediaType.type
    }
    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}