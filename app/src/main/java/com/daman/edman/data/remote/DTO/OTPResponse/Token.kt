package com.daman.edman.data.remote.DTO.OTPResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Token(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_in")
    val expiresIn: Int?,
    @Json(name = "token_type")
    val tokenType: String?
)