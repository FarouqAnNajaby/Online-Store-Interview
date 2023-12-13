package com.example.onlinestorefarouq.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinestorefarouq.R
import com.example.onlinestorefarouq.databinding.ActivityMainBinding
import com.example.onlinestorefarouq.helper.Constanta
import com.example.onlinestorefarouq.ui.auth.LoginRegisterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@MainActivity, LoginRegisterActivity::class.java)
            intent.putExtra(Constanta.LABEL,Constanta.IS_LOGIN)
            startActivity(intent)
        }
        binding.btnRegist.setOnClickListener{
            val intent = Intent(this@MainActivity, LoginRegisterActivity::class.java)
            intent.putExtra(Constanta.LABEL,Constanta.IS_REGISTER)
            startActivity(intent)
        }

    }
}