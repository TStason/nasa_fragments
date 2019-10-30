package com.example.nasa_api.common_classes

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.serialization.Serializable

data class Item(val href: String, val links: List<Link>? = null, val data: List<NasaData>? = null) {
}

data class Link(val href: String, val render: String, val rel: String)
@Serializable
data class NasaData(
    @SerializedName("nasa_id")
    @Expose
    val nasaId: String,
    val title: String?=null,
    val keywords: List<String>?=null,
    val center: String?=null,
    @SerializedName("date_created")
    @Expose
    val dateCreated: String?=null,
    @SerializedName("media_type")
    @Expose
    val mediaType: MediaType,
    val photographer: String?=null,
    val description: String?=null
    //val other: Map<String, Any?>?=null
): java.io.Serializable

class Collection(
    val links: List<CollLink>? = null,
    val version: String? = null,
    val items: List<Item>? = null,
    val href: String? = null,
    val metadata: Meta? = null,
    val other: Map<String, Any?>)

data class CollLink( val rel: String,//? = null,
                     val prompt: String,//? = null,
                     val href: String//? = null
)
data class Meta(@SerializedName("total_hits") val totalHits: Int? = null)

data class SearchResponse(val collection: Collection)

enum class MediaType(val type: Int){
    @SerializedName("video")
    VIDEO(0),
    @SerializedName("audio")
    AUDIO(1),
    @SerializedName("image")
    IMAGE(2)
}