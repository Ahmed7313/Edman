package com.daman.edman.data.remote.DTO.OrderDTO


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SellerId(
    @Json(name = "balance")
    val balance: String?,
    @Json(name = "code")
    val code: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: Any?,
    @Json(name = "isProfileCompleted")
    val isProfileCompleted: Boolean?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "phone")
    val phone: String?
)