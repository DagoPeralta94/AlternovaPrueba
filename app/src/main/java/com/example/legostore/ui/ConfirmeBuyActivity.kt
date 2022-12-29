package com.example.legostore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.legostore.MainActivity
import com.example.legostore.R
import com.example.legostore.data.ProductDb
import com.example.legostore.data.ProductDbClient
import com.example.legostore.data.ProductDescriptionDb
import com.example.legostore.databinding.ActivityConfirmeBuyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConfirmeBuyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmeBuyBinding
    lateinit var productDescList: List<ProductDb>
    var stockProvF = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmeBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        stockProvF = bundle?.getString("stockProvF").toString()

        runBlocking {
            launch(Dispatchers.IO) {
                sendPostBuy()
            }
        }

        binding.txtItemSold.text = ("$stockProvF")

        binding.btAgain.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btExit.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private suspend fun sendPostBuy() {
        var productDblist = ProductDbClient.service.requestBuy()
        productDescList = listOf(productDblist)
    }

}