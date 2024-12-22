package com.daman.edman.data.remote.DTO.OTPResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "balance")
    val balance: String? = "",
    @Json(name = "code")
    val code: String? = "",
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "isProfileCompleted")
    val isProfileCompleted: Boolean? = false,
    @Json(name = "language")
    val language: String? = "",
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "phone")
    val phone: String = ""
)