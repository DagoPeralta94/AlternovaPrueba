package com.example.legostore.core

import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDescriptionDb
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("all-products")
    suspend fun listAllProducts(@Query("api_key") apiKey: String): ProductDb

    @GET("detail/{id}")
    suspend fun listDetailProducts(@Path("id") id: String): ProductDescriptionDb

}
