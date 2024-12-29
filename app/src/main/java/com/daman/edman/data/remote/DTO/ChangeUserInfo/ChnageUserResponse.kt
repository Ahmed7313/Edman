package com.daman.edman.data.remote.DTO.ChangeUserInfo


import com.daman.edman.data.remote.DTO.OTPResponse.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompleteUserResponse(
    @Json(name = "data")
    val `data`: User?,
    @Json(name = "msg")
    val msg: String?,
    @Json(name = "status")
    val status: Boolean?
)