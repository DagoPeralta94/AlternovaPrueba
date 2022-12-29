package com.example.legostore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.legostore.R
import com.example.legostore.aplications.AppConstants
import com.example.legostore.core.adapter.ProductsAdapter
import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ActivityProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    lateinit var productList: List<ProductsDetailsDb>
    var idProv: String = ""

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
            rvProducts.adapter = ProductsAdapter(productList)  { onItemSelected(it) }
            ProductsAdapter(productList)  { onItemSelected(it) } .notifyDataSetChanged()
        }
    }

    private suspend fun launchData() {
        var productDblist = ProductDbClient.service.listAllProducts(AppConstants.API_KEY)
        productList = productDblist.products
    }

    private fun onItemSelected(listDetailProv: ProductsDetailsDb){
        idProv = listDetailProv.id.toString()
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("direccion", idProv)
        startActivity(intent)
    }
}
