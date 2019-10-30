package com.example.nasa_api.ui.recycler_thing

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_api.R
import com.example.nasa_api.common_classes.NasaNode
import kotlin.math.min

abstract class NasaViewHolder(view: View, private val interaction: Interaction?=null): RecyclerView.ViewHolder(view) {

    fun bind(item: NasaNode){
        itemView.findViewById<TextView>(R.id.media_title).text = item.nasaData.title
        // photographer or center
        itemView.findViewById<TextView>(R.id.media_owner).apply {
            if (item.nasaData.photographer != null)
                text = item.nasaData.photographer
            else
                text = item.nasaData.center
        }
        itemView.findViewById<TextView>(R.id.media_date_created).text = item.nasaData.dateCreated
        itemView.findViewById<TextView>(R.id.media_description).text =
            item.nasaData.description?.substring(0, min(50, item.nasaData.description.length))
        itemView.findViewById<TextView>(R.id.media_keywords).text = item.nasaData.keywords?.toString()
        setMedia(item.mediaLinks)
        itemView.setOnClickListener {
            interaction?.onItemSelected(item)
        }
    }

    abstract fun setMedia(links: List<String>)

    fun removeInteraction(){
        itemView.setOnClickListener(null)
    }
}