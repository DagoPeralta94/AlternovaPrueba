package com.example.legostore.core

import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductsDetailsDb
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("all-products")
    suspend fun listAllProducts(@Query("api_key") apiKey: String): ProductDb

    @GET("detail")
    suspend fun listDetailProducts(@Query("id") idProduct: String): ProductsDetailsDb

}
