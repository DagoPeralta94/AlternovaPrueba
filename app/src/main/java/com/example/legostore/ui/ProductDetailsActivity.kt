package com.example.legostore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.legostore.aplications.AppConstants
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.data.ProductsDetailsDb
import com.example.legostore.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    lateinit var productDescList: ProductsDetailsDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            launch(Dispatchers.IO) {
                launchData()
            }
        }
    }

    private suspend fun launchData() {
        val bundle = intent.extras
        val dato = bundle?.getString("idProvF").toString()
        binding.tvIdProv.text = dato.toString()
        var productDblist = ProductDbClient.service.listDetailProducts(dato)
        productDescList = productDblist
    }

}