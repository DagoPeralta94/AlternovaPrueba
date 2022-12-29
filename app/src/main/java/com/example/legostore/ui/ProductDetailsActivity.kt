package com.example.legostore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.legostore.core.adapter.DetailsProdAdapter
import com.example.legostore.core.adapter.ProductsAdapter
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    lateinit var productDescList: List<ProductDescriptionDb>
    private var dato = "0"
    var stockProv: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        dato = bundle?.getString("idProvF").toString()

        runBlocking {
            launch(Dispatchers.IO) {
                launchData()
            }
        }
        launchRecyclerViewMain()
    }

    private suspend fun launchData() {

        var productDescDbList = ProductDbClient.service.listDetailProducts(dato)
        productDescList = listOf(productDescDbList)
    }

    private fun launchRecyclerViewMain() {
        with(binding) {
            rvDetailProduct.layoutManager = LinearLayoutManager(
                rvDetailProduct.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            rvDetailProduct.adapter = DetailsProdAdapter(productDescList, { alertDialogShow(it) })
            DetailsProdAdapter(productDescList, { alertDialogShow(it) }).notifyDataSetChanged()
        }
    }

    private fun alertDialogShow(listModelAllProducts: ProductDescriptionDb) =
        if (listModelAllProducts.stockSold != 0) {
            val builder = AlertDialog.Builder(binding.root.context)
            builder.setTitle("CONFIRM PRODUCTS")
            builder.setMessage("Item: ${listModelAllProducts.name}\nMount: ${listModelAllProducts.stockSold}")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("YES") { dialogInterface, which ->
                Toast.makeText(binding.root.context, "Buy Confirmed", Toast.LENGTH_SHORT).show()
                onBuySelected(listModelAllProducts)
            }
            builder.setNegativeButton("NO") { dialogInterface, which ->
                Toast.makeText(binding.root.context, "Buy Cancel", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        } else {
            Toast.makeText(binding.root.context, "Add some products", Toast.LENGTH_SHORT).show()
        }

    private fun onBuySelected(listDetailProv: ProductDescriptionDb) {
        stockProv = listDetailProv.name
        finish()
        val intent = Intent(this, ConfirmeBuyActivity::class.java)
        intent.putExtra("stockProvF", stockProv)
        startActivity(intent)
    }

}