package com.aramex.mypos.Domain.ReposInterfaces

import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import okhttp3.RequestBody


interface IHomeRepository {


    suspend fun completeUserData (body: RequestBody): CompleteUserResponse

}