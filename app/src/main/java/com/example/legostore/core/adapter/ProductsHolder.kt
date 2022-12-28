package com.example.legostore.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.legostore.aplications.AppConstants
import com.example.legostore.data.ProductDb
import com.example.legostore.databinding.ItemProductsBinding

class ProductsHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemProductsBinding.bind(view)

    fun render(listModelAllProducts: ProductDb) {
        with(binding) {
            Glide.with(ivMovies.context).load("${AppConstants.BASE_URL_IMAGE}${listModelAllProducts.image}")
                .into(ivMovies)
            tvProductName.text = listModelAllProducts.name
            tvProductPrice.text = listModelAllProducts.unit_price.toString()
            tvProductStock.text = listModelAllProducts.stock.toString()
        }
    }
}