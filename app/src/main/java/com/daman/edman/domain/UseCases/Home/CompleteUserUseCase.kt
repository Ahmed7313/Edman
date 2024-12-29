package com.daman.edman.domain.UseCases.Home

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.ReposInterfaces.IRegistrationRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.Login.LoginResponseDTO
import okhttp3.RequestBody
import javax.inject.Inject

class CompleteUserUseCase @Inject constructor(
    private val repository: IHomeRepository
) : BaseUseCase<RequestBody, CompleteUserResponse>() {
    override suspend fun execute(params: RequestBody?): CompleteUserResponse {
        requireNotNull(params) { "RequestBody must not be null" }
        return repository.completeUserData(params)
    }
}