package com.daman.edman.domain.UseCases.Home

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.OrderDTO.AllOrdersResponseDTO
import javax.inject.Inject

class GetAllOrdersUseCase @Inject constructor(
    private val repository: IHomeRepository
) : BaseUseCase<Int?, AllOrdersResponseDTO>() {
    override suspend fun execute(params: Int?): AllOrdersResponseDTO {
        return repository.getAllOrders(params)
    }
}
