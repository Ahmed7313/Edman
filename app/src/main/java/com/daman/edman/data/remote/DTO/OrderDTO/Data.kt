package com.daman.edman.data.remote.DTO.OrderDTO


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "buyerId")
    val buyerId: BuyerId?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "deliveryDate")
    val deliveryDate: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_owned")
    val isOwned: Boolean?,
    @Json(name = "officialPageUrl")
    val officialPageUrl: String?,
    @Json(name = "orderNumber")
    val orderNumber: String?,
    @Json(name = "paymentTypeId")
    val paymentTypeId: Any?,
    @Json(name = "price")
    val price: String?,
    @Json(name = "product")
    val product: String?,
    @Json(name = "productImage")
    val productImage: Any?,
    @Json(name = "productOrderUrl")
    val productOrderUrl: String?,
    @Json(name = "productStatus")
    val productStatus: Int?,
    @Json(name = "sellerId")
    val sellerId: SellerId?,
    @Json(name = "status")
    val status: Int?,
    @Json(name = "tax")
    val tax: String?,
    @Json(name = "totalPrice")
    val totalPrice: String?
)