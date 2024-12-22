package com.daman.edman.domain.UseCases.Auth

import com.aramex.mypos.Domain.ReposInterfaces.IRegistrationRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.Login.LoginResponseDTO
import com.daman.edman.data.remote.DTO.OTPResponse.CodeResponse
import okhttp3.RequestBody
import javax.inject.Inject

class CheckCodeUseCase @Inject constructor(
    private val repository: IRegistrationRepository
) : BaseUseCase<RequestBody, CodeResponse>() {
    override suspend fun execute(params: RequestBody?): CodeResponse {
        requireNotNull(params) { "RequestBody must not be null" }
        return repository.checkCode(params)
    }
}