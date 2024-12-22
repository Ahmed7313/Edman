package com.aramex.mypos.Domain.ReposInterfaces


import com.daman.edman.data.remote.DTO.Login.LoginResponseDTO
import com.daman.edman.data.remote.DTO.OTPResponse.CodeResponse
import okhttp3.RequestBody

interface IRegistrationRepository {

suspend fun login(body: RequestBody) : LoginResponseDTO

suspend fun checkCode(body: RequestBody) : CodeResponse
}