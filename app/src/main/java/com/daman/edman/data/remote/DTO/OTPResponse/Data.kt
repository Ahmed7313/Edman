package com.daman.edman.data.remote.DTO.OTPResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "token")
    val token: Token?,
    @Json(name = "user")
    val user: User?
)