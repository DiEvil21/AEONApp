package ru.dievil.aeonapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.dievil.aeonapp.network.RetrofitInstance
import ru.dievil.aeonapp.model.LoginRequest
import ru.dievil.aeonapp.model.Payment

class AuthViewModel : ViewModel() {

    fun login(loginRequest: LoginRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.login(
                appKey = "12345",
                version = "1",
                loginRequest = loginRequest
            )

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.success == "true") {
                    emit(responseBody.response.token)
                } else {
                    emit("")
                }
            } else {
                emit("")
            }
        } catch (e: Exception) {
            // Обработка ошибок, если они возникнут при выполнении запроса
            emit("")
        }
    }

    fun getPayments(token: String) = liveData(Dispatchers.IO) {
        val response = RetrofitInstance.api.getPayments(
            appKey = "12345",
            version = "1",
            token = token
        )
        if (response.isSuccessful) {
            emit(response.body() ?: emptyList())
        } else {
            emit(emptyList<Payment>())
        }
    }
}
