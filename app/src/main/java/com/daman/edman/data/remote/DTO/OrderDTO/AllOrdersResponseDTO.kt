package com.daman.edman.data.remote.DTO.OrderDTO

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllOrdersResponseDTO(
    @Json(name = "data")
    val `data`: List<Data>?,
    @Json(name = "msg")
    val msg: String?,
    @Json(name = "status")
    val status: Boolean?
)
