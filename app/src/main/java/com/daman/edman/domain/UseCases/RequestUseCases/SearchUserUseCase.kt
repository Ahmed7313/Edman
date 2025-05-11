package com.daman.edman.domain.UseCases.RequestUseCases

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val repository: IHomeRepository
) : BaseUseCase<String, SearchDTO>() {
    override suspend fun execute(params: String?): SearchDTO {
        requireNotNull(params) { "Phone number must not be null" }
        return repository.searchUser(params)
    }
} 