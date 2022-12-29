package com.example.legostore.data

data class ProductDescriptionDb(
    val name: String,
    val unit_price: Int,
    val stock: Int,
    val description: String,
    val image: String
)
