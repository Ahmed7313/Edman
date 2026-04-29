package com.aramex.mypos.Data.Repos

import com.aramex.mypos.Data.remote.ApiServices

import com.aramex.mypos.Domain.ReposInterfaces.IHomeRepository
import com.aramex.mypos.Domain.ReposInterfaces.IRegistrationRepository
import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.OrderDTO.AllOrdersResponseDTO
import com.daman.edman.data.remote.DTO.OrderDTO.OrderDTO
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import com.daman.edman.data.remote.DTO.Wallet.InstapayAccountsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.TransactionsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.WalletActionResponseDTO
import okhttp3.RequestBody
import javax.inject.Inject

class HomeRepository  @Inject constructor(
    private val services: ApiServices,
) : IHomeRepository {
    override suspend fun completeUserData(body: RequestBody) = services.completeUserData(body)
    override suspend fun searchUser(phone: String): SearchDTO = services.searchUser(phone)
    override suspend fun createOrder(body: RequestBody) = services.createOrder(body)
    override suspend fun getAllOrders(status: Int?): AllOrdersResponseDTO = services.getAllOrders(status)
    override suspend fun updateProfileInfo(body: RequestBody): CompleteUserResponse = services.updateProfileInfo(body)
    override suspend fun getTransactions(): TransactionsResponseDTO = services.getTransactions()
    override suspend fun rechargeWallet(body: RequestBody): WalletActionResponseDTO = services.rechargeWallet(body)
    override suspend fun withdrawWallet(body: RequestBody): WalletActionResponseDTO = services.withdrawWallet(body)
    override suspend fun getInstapayAccounts(): InstapayAccountsResponseDTO = services.getInstapayAccounts()
}
