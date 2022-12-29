package com.example.legostore.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ItemDetailsProductBinding
import com.example.legostore.databinding.ItemProductsBinding

class DetailsProdHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemDetailsProductBinding.bind(view)

    fun render(listModelDescProd: ProductDescriptionDb) {
        with(binding) {
            Glide.with(ivMovies.context).load("${listModelDescProd.image}")
                .into(ivMovies)
            tvProductName.text = listModelDescProd.name
            tvProductPrice.text = ("Price: ${listModelDescProd.unit_price.toString()}")
            tvProductStock.text = ("Stock: ${listModelDescProd.stock.toString()}")
            tvDescription.text = listModelDescProd.description
        }
    }
}