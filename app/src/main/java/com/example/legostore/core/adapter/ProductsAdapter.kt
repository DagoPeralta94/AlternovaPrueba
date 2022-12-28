package com.example.legostore.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.legostore.R
import com.example.legostore.data.ProductsDetailsDb

class ProductsAdapter(var productAllList: List<ProductsDetailsDb>, private val onClickListener:(ProductsDetailsDb) -> Unit): RecyclerView.Adapter<ProductsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsHolder(layoutInflater.inflate(R.layout.item_products, parent, false))
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val item = productAllList[position]
        return holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = productAllList.size

}
