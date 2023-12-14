package com.example.onlinestorefarouq.ui.addproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinestorefarouq.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}