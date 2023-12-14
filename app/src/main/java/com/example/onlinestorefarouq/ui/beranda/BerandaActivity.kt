package com.example.onlinestorefarouq.ui.beranda

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onlinestorefarouq.R
import com.example.onlinestorefarouq.databinding.ActivityBerandaBinding
import com.example.onlinestorefarouq.databinding.DialogNormalBinding
import com.example.onlinestorefarouq.ui.MainActivity

class BerandaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener { logout() }
        binding.btnAddProduct.setOnClickListener {  }
        binding.btnBack.setOnClickListener {  }
        binding.edtSearch.setOnClickListener {  }
    }

    private fun logout() {
        val dialog = Dialog(this)
        val dialogBinding: DialogNormalBinding =
            DialogNormalBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)

        dialogBinding.textTitle.text = resources.getString(R.string.text_logout)
        dialogBinding.buttonNo.text = resources.getString(R.string.text_no)
        dialogBinding.buttonYes.text = resources.getString(R.string.text_yes)
        dialog.show()

        dialogBinding.buttonYes.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this@BerandaActivity, MainActivity::class.java)
            startActivity(intent)
        }
        dialogBinding.buttonNo.setOnClickListener { dialog.dismiss() }
    }
}