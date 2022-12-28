package com.example.legostore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            rvProducts.adapter = ProductsAdapter(productList)
            ProductsAdapter(productList).notifyDataSetChanged()
        }
    }

    private suspend fun launchData() {
        var productDblist = ProductDbClient.service.listAllProducts(AppConstants.API_KEY)
        productList = productDblist.products
    }
}
