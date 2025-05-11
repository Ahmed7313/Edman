package com.daman.edman.data.remote.DTO.SearchDTO


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchDTO(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "msg")
    val msg: String?,
    @Json(name = "status")
    val status: Boolean?
)