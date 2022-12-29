package com.example.legostore.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.legostore.R
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.data.ProductsDetailsDb

class DetailsProdAdapter(var detailList: List<ProductDescriptionDb>): RecyclerView.Adapter<DetailsProdHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsProdHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetailsProdHolder(layoutInflater.inflate(R.layout.item_details_product, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsProdHolder, position: Int) {
        val item = detailList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = detailList.size
}