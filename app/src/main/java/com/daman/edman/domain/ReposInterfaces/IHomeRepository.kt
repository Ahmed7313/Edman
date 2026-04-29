package com.aramex.mypos.Domain.ReposInterfaces

import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import okhttp3.RequestBody
import com.daman.edman.data.remote.DTO.OrderDTO.AllOrdersResponseDTO
import com.daman.edman.data.remote.DTO.OrderDTO.OrderDTO
import com.daman.edman.data.remote.DTO.Wallet.InstapayAccountsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.TransactionsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.WalletActionResponseDTO
import okhttp3.MultipartBody

interface IHomeRepository {
    suspend fun completeUserData(body: RequestBody): CompleteUserResponse
    suspend fun searchUser(phone: String): SearchDTO
    suspend fun createOrder(body: RequestBody): OrderDTO
    suspend fun getAllOrders(status: Int? = null): AllOrdersResponseDTO
    suspend fun updateProfileInfo(body: RequestBody): CompleteUserResponse
    suspend fun getTransactions(): TransactionsResponseDTO
    suspend fun rechargeWallet(body: RequestBody): WalletActionResponseDTO
    suspend fun withdrawWallet(body: RequestBody): WalletActionResponseDTO
    suspend fun getInstapayAccounts(): InstapayAccountsResponseDTO
}