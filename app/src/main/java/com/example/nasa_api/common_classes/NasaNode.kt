package com.example.nasa_api.common_classes

import kotlinx.serialization.Serializable

@Serializable
data class NasaNode(val nasaData: NasaData, val mediaLinks: List<String>): java.io.Serializable