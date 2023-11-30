package ru.dievil.aeonapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {
    private const val BASE_URL = "https://easypay.world/api-test/"

    // Добавьте объекты для работы с JSON
    private val gson = GsonBuilder().setLenient().create()

    // Создайте OkHttpClient с логгированием
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    // Создайте Retrofit с использованием OkHttpClient
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    // Создайте API-интерфейс
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}


