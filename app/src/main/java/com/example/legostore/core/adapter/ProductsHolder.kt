package com.example.legostore.core.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.legostore.aplications.AppConstants
import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ItemProductsBinding
import com.example.legostore.ui.ProductDetailsActivity
import kotlin.system.exitProcess

class ProductsHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemProductsBinding.bind(view)
    var countPlus: Int = 0
    lateinit var productDescList: List<ProductDb>

    fun render(
        listModelAllProducts: ProductsDetailsDb,
        onClickListener: (ProductsDetailsDb) -> Unit
    ) {
        with(binding) {
            Glide.with(ivMovies.context).load("${listModelAllProducts.image}")
                .into(ivMovies)
            tvProductName.text = listModelAllProducts.name
            tvProductPrice.text = ("Price: ${listModelAllProducts.unit_price.toString()}")
            tvProductStock.text = ("Stock: ${listModelAllProducts.stock.toString()}")
            txMountToBuy.text = countPlus.toString()
            setButtonMount(listModelAllProducts)
            setNoStock(listModelAllProducts)

            itemView.setOnClickListener { onClickListener(listModelAllProducts) }


            btPlus.setOnClickListener {
                setBtPlus(listModelAllProducts)
            }
            btMenus.setOnClickListener {
                setBtMenus(listModelAllProducts)
            }
            btBuy.setOnClickListener {
                alertDialogShow(listModelAllProducts)
            }
        }
    }

    private fun setBtMenus(listModelAllProducts: ProductsDetailsDb) {
        with(binding){
            if (txMountToBuy.text.toString().toInt() <= listModelAllProducts.stock && txMountToBuy.text.toString().toInt() != 0) {
                countPlus -= 1
                txMountToBuy.text = countPlus.toString()
            }
            setButtonMount(listModelAllProducts)
        }
    }

    private fun setBtPlus(listModelAllProducts: ProductsDetailsDb){
        with(binding){
            if (countPlus >= 0 && txMountToBuy.text.toString().toInt() != listModelAllProducts.stock) {
                countPlus += 1
                txMountToBuy.text = countPlus.toString()
            }
            setButtonMount(listModelAllProducts)
        }
    }

    private fun setNoStock(listModelAllProducts: ProductsDetailsDb) {
        if (listModelAllProducts.stock.toString().toInt() == 0) {
            with(binding) {
                btBuy.isEnabled = false
                btMenus.isEnabled = false
                btPlus.isEnabled = false
            }
        }
    }

    private fun setButtonMount(listModelAllProducts: ProductsDetailsDb) {
        binding.btMenus.isEnabled = binding.txMountToBuy.text.toString().toInt() != 0
        binding.btPlus.isEnabled = binding.txMountToBuy.text.toString().toInt() != listModelAllProducts.stock
    }

    private fun alertDialogShow(listModelAllProducts: ProductsDetailsDb) =
        if(binding.txMountToBuy.text.toString().toInt() != 0){
            val builder = AlertDialog.Builder(binding.btBuy.context)
            builder.setTitle("CONFIRM PRODUCTS")
            builder.setMessage("Item: ${listModelAllProducts.name}\nMount: ${binding.txMountToBuy.text}")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("YES") { dialogInterface, which ->
                Toast.makeText(binding.btMenus.context, "Buy Confirmed", Toast.LENGTH_SHORT).show()
                setDefaultValues(listModelAllProducts)
            }
            builder.setNegativeButton("NO") { dialogInterface, which ->
                Toast.makeText(binding.btMenus.context, "Buy Cancel", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }else{
            Toast.makeText(binding.btMenus.context, "Add some products", Toast.LENGTH_SHORT).show()
        }

    private fun setDefaultValues(listModelAllProducts: ProductsDetailsDb){
        binding.txMountToBuy.text = "0"
        countPlus = 0
        setButtonMount(listModelAllProducts)
    }


}