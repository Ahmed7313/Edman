package com.daman.edman.domain.UseCases.Home

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: IHomeRepository
) : BaseUseCase<RequestBody, CompleteUserResponse>() {
    override suspend fun execute(params: RequestBody?): CompleteUserResponse {
        requireNotNull(params) { "RequestBody must not be null" }
        return repository.updateProfileInfo(params)
    }
}
