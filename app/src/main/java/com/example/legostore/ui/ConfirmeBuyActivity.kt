package com.example.legostore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.legostore.MainActivity
import com.example.legostore.R
import com.example.legostore.databinding.ActivityConfirmeBuyBinding

class ConfirmeBuyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmeBuyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmeBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}