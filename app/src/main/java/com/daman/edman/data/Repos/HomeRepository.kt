package com.aramex.mypos.Data.Repos

import com.aramex.mypos.Data.remote.ApiServices

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.ReposInterfaces.IRegistrationRepository
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import okhttp3.RequestBody
import javax.inject.Inject

class HomeRepository  @Inject constructor(
    private val services: ApiServices,
) : IHomeRepository {
    override suspend fun completeUserData(body: RequestBody) = services.completeUserData(body)

    override suspend fun searchUser(phone: String): SearchDTO = services.searchUser(phone)
}
