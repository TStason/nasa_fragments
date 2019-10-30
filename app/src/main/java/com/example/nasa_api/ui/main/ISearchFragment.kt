package com.example.nasa_api.ui.main

import com.example.nasa_api.common_classes.NasaNode
import com.example.nasa_api.ui.base.DynamicFragment

interface ISearchFragment: DynamicFragment {
    fun clearRecycler()
    fun updateRecycler(node: List<NasaNode>)
    fun toast(msg: String)
}