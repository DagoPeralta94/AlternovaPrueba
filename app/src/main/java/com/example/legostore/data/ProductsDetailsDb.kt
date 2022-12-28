package com.example.legostore.data

data class ProductsDetailsDb(
    val id: Int,
    val name: String,
    val unit_price: Int,
    val stock: Int,
    val image: String
)
