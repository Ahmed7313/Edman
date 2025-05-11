package com.aramex.mypos.Domain.ReposInterfaces

import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import okhttp3.RequestBody
import com.daman.edman.data.remote.DTO.OrderDTO.OrderDTO
import okhttp3.MultipartBody

interface IHomeRepository {
    suspend fun completeUserData(body: RequestBody): CompleteUserResponse
    suspend fun searchUser(phone: String): SearchDTO
    suspend fun createOrder(body: RequestBody): OrderDTO
}