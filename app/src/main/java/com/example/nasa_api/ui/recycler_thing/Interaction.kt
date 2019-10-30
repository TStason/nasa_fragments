package com.example.nasa_api.ui.recycler_thing

import com.example.nasa_api.common_classes.NasaNode

interface Interaction {
    fun onItemSelected(item: NasaNode)
}