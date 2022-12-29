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
    var countPlus: Int = 0

    fun render(listModelDescProd: ProductDescriptionDb,
               onBuyListener: (ProductDescriptionDb) -> Unit) {
        with(binding) {
            Glide.with(ivMovies.context).load("${listModelDescProd.image}")
                .into(ivMovies)
            tvProductName.text = listModelDescProd.name
            tvProductPrice.text = ("Price: ${listModelDescProd.unit_price.toString()}")
            tvProductStock.text = ("Stock: ${listModelDescProd.stock.toString()}")
            tvDescription.text = listModelDescProd.description
            setButtonMount(listModelDescProd)
            setNoStock(listModelDescProd)

            btBuy.setOnClickListener { onBuyListener(listModelDescProd) }

            btPlus.setOnClickListener {
                setBtPlus(listModelDescProd)
            }
            btMenus.setOnClickListener {
                setBtMenus(listModelDescProd)
            }
        }
    }


    private fun setBtMenus(listModelAllProducts: ProductDescriptionDb) {
        with(binding){
            if (txMountToBuy.text.toString().toInt() <= listModelAllProducts.stock && txMountToBuy.text.toString().toInt() != 0) {
                countPlus -= 1
                txMountToBuy.text = countPlus.toString()
                listModelAllProducts.stockSold -= 1
            }
            setButtonMount(listModelAllProducts)
        }
    }

    private fun setBtPlus(listModelAllProducts: ProductDescriptionDb) : Int{
        with(binding){
            if (countPlus >= 0 && txMountToBuy.text.toString().toInt() != listModelAllProducts.stock) {
                countPlus += 1
                txMountToBuy.text = countPlus.toString()
                listModelAllProducts.stockSold += 1
            }
            setButtonMount(listModelAllProducts)
        }
        return countPlus
    }

    private fun setButtonMount(listModelAllProducts: ProductDescriptionDb) {
        binding.btMenus.isEnabled = binding.txMountToBuy.text.toString().toInt() != 0
        binding.btPlus.isEnabled = binding.txMountToBuy.text.toString().toInt() != listModelAllProducts.stock
    }

    private fun setNoStock(listModelAllProducts: ProductDescriptionDb) {
        if (listModelAllProducts.stock.toString().toInt() == 0) {
            with(binding) {
                btBuy.isEnabled = false
                btMenus.isEnabled = false
                btPlus.isEnabled = false
            }
        }
    }

}