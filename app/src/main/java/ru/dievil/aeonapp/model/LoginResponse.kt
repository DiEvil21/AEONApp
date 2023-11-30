package ru.dievil.aeonapp.model

data class LoginResponse(
    val success: String,
    val response: ResponseData
)

data class ResponseData(
    val token: String
)
