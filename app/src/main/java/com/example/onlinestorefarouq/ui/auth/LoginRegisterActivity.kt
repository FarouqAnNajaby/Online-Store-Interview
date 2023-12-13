package com.example.onlinestorefarouq.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinestorefarouq.databinding.ActivityLoginRegisterBinding

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {  }
    }
}