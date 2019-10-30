package com.example.nasa_api.common_classes

data class NasaRequest(val q: String?=null, val center: String?=null, val page: Int?=null, val nasaId: String?=null) {
}