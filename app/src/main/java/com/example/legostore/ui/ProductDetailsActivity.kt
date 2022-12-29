package com.example.legostore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.legostore.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val dato = bundle?.getString("direccion")
        binding.tvIdProv.text = dato.toString()
    }
}