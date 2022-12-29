package com.example.legostore.core

import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDescriptionDb
import retrofit2.http.*

interface ApiService {
    @GET("all-products")
    suspend fun listAllProducts(@Query("api_key") apiKey: String): ProductDb

    @GET("detail/{id}")
    suspend fun listDetailProducts(@Path("id") id: String): ProductDescriptionDb

    @POST("buy")
    suspend fun requestBuy(): ProductDb

}
