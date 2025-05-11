package com.daman.edman.domain.UseCases.Home.CreateOrder

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.UseCases.BaseUseCase
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.OrderDTO.OrderDTO
import okhttp3.RequestBody
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val repository: IHomeRepository
) : BaseUseCase<RequestBody, OrderDTO>() {
    override suspend fun execute(params: RequestBody?): OrderDTO {
        requireNotNull(params) { "RequestBody must not be null" }
        return repository.createOrder(params)
    }
}