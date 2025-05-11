package com.aramex.mypos.Domain.ReposInterfaces

import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import okhttp3.RequestBody

interface IHomeRepository {
    suspend fun completeUserData(body: RequestBody): CompleteUserResponse
    suspend fun searchUser(phone: String): SearchDTO
}