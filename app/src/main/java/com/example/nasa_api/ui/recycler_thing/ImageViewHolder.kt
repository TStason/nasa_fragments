package com.example.nasa_api.ui.recycler_thing

import android.view.View
import com.bumptech.glide.Glide
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode

class ImageViewHolder(view: View, interaction: Interaction?): NasaViewHolder(view, interaction) {
    override fun setMedia(links: List<String>) {
        Glide.with(itemView)
            .load(links.find { it.contains("thumb", true) })
            .centerCrop()
            .into(itemView.findViewById(R.id.media_content))
    }
}