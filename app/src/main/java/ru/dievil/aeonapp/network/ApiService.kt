package ru.dievil.aeonapp.network

import retrofit2.Response
import retrofit2.http.*
import ru.dievil.aeonapp.model.LoginRequest
import ru.dievil.aeonapp.model.LoginResponse
import ru.dievil.aeonapp.model.Payment

interface ApiService {
    @POST("login")
    suspend fun login(
        @Header("app-key") appKey: String,
        @Header("v") version: String,
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("payments")
    suspend fun getPayments(
        @Header("app-key") appKey: String,
        @Header("v") version: String,
        @Header("token") token: String
    ): Response<List<Payment>>
}

