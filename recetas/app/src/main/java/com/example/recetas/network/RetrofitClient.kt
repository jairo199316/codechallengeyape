package com.example.recetas.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://demo1366925.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}