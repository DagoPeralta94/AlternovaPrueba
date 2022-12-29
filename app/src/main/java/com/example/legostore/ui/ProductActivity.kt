package com.example.legostore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.legostore.aplications.AppConstants
import com.example.legostore.core.adapter.ProductsAdapter
import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ActivityProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    lateinit var productList: List<ProductsDetailsDb>
    lateinit var productListProv: List<ProductsDetailsDb>
    var idProv: String = ""
    var stockProv: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runBlocking {
            launch(Dispatchers.IO) {
                launchData()
            }
        }
        launchRecyclerViewMain()
    }

    private fun launchRecyclerViewMain() {
        with(binding) {
            rvProducts.layoutManager = LinearLayoutManager(
                rvProducts.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            rvProducts.adapter = ProductsAdapter(productList, { onItemSelected(it) }, { alertDialogShow(it) })
            ProductsAdapter(productList, { onItemSelected(it) }, { onBuySelected(it) }).notifyDataSetChanged()
        }
    }

    private suspend fun launchData() {
        var productDblist = ProductDbClient.service.listAllProducts(AppConstants.API_KEY)
        productList = productDblist.products
        productListProv = productDblist.products
    }

    private fun onItemSelected(listDetailProv: ProductsDetailsDb){
        idProv = listDetailProv.id.toString()
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("idProvF", idProv)
        startActivity(intent)
    }

    private fun onBuySelected(listDetailProv: ProductsDetailsDb){
        stockProv = listDetailProv.stockSold.toString()
        val intent = Intent(this, ConfirmeBuyActivity::class.java)
        intent.putExtra("stockProvF", stockProv)
        startActivity(intent)
    }

    private suspend fun sendPostBuy() {
        var productDblist = ProductDbClient.service.requestBuy()
        productList = productDblist.products
    }

    private fun alertDialogShow(listModelAllProducts: ProductsDetailsDb) =
        if(listModelAllProducts.stockSold != 0){
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
        }else{
            Toast.makeText(binding.root.context, "Add some products", Toast.LENGTH_SHORT).show()
        }

}
