package com.example.onlinestorefarouq.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.onlinestorefarouq.databinding.ActivityMainBinding
import com.example.onlinestorefarouq.helper.Constanta
import com.example.onlinestorefarouq.repository.data.UserPreference
import com.example.onlinestorefarouq.repository.data.model.User
import com.example.onlinestorefarouq.ui.auth.LoginRegisterActivity
import com.example.onlinestorefarouq.ui.beranda.BerandaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userModel: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreference = UserPreference(this)
        userModel = userPreference.getUser()

        if (userModel.email?.isNotEmpty() == true){
            binding.loadingView.isVisible = true
            val intent = Intent(this@MainActivity, BerandaActivity::class.java)
            intent.putExtra(Constanta.LABEL,Constanta.IS_LOGIN)
            binding.loadingView.isVisible = false
            startActivity(intent)
        }

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