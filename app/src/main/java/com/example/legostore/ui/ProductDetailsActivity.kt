package com.example.legostore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.legostore.core.adapter.DetailsProdAdapter
import com.example.legostore.core.adapter.ProductsAdapter
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    lateinit var productDescList: List<ProductDescriptionDb>
    private var dato = "0"

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
            rvDetailProduct.adapter = DetailsProdAdapter(productDescList)
            DetailsProdAdapter(productDescList).notifyDataSetChanged()
        }
    }

}