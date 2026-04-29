package com.aramex.mypos.Data.remote


import com.daman.edman.data.remote.DTO.ChangeUserInfo.CompleteUserResponse
import com.daman.edman.data.remote.DTO.Login.LoginResponseDTO
import com.daman.edman.data.remote.DTO.OTPResponse.CodeResponse
import com.daman.edman.data.remote.DTO.OrderDTO.AllOrdersResponseDTO
import com.daman.edman.data.remote.DTO.OrderDTO.OrderDTO
import com.daman.edman.data.remote.DTO.SearchDTO.SearchDTO
import com.daman.edman.data.remote.DTO.Wallet.InstapayAccountsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.TransactionsResponseDTO
import com.daman.edman.data.remote.DTO.Wallet.WalletActionResponseDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    @POST("auth/login")
    suspend fun login(
        @Body body: RequestBody
    ): LoginResponseDTO

    @POST("auth/checkCode")
    suspend fun checkCode(
        @Body body: RequestBody
    ): CodeResponse

    @POST("user/completeInfo")
    suspend fun completeUserData(
        @Body body: RequestBody,
    ): CompleteUserResponse

    @POST("user/search")
    suspend fun searchUser(
        @Query("phone") phone: String
    ): SearchDTO

    @POST("order")
    suspend fun createOrder(
        @Body body: RequestBody
    ): OrderDTO

    @GET("order/all")
    suspend fun getAllOrders(
        @Query("status") status: Int? = null
    ): AllOrdersResponseDTO

    @POST("user/updateProfileInfo")
    suspend fun updateProfileInfo(
        @Body body: RequestBody
    ): CompleteUserResponse

    // ── Wallet ────────────────────────────────────────────────────────────────

    @GET("user/transactions")
    suspend fun getTransactions(): TransactionsResponseDTO

    @POST("user/rechargeWallet")
    suspend fun rechargeWallet(
        @Body body: RequestBody
    ): WalletActionResponseDTO

    @POST("user/withdrawWallet")
    suspend fun withdrawWallet(
        @Body body: RequestBody
    ): WalletActionResponseDTO

    @GET("payment/instapayAccounts")
    suspend fun getInstapayAccounts(): InstapayAccountsResponseDTO
}