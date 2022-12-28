package com.example.legostore.data

import com.example.legostore.aplications.AppConstants
import com.example.legostore.core.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductDbClient {
    private val retrofit = Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)
}