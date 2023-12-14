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
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinestorefarouq.R
import com.example.onlinestorefarouq.databinding.ActivityBerandaBinding
import com.example.onlinestorefarouq.databinding.DialogNormalBinding
import com.example.onlinestorefarouq.repository.data.UserPreference
import com.example.onlinestorefarouq.repository.data.model.Product
import com.example.onlinestorefarouq.ui.MainActivity
import com.example.onlinestorefarouq.ui.addproduct.AddProductActivity
import com.example.onlinestorefarouq.ui.auth.AuthViewModel

class BerandaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectViewModel()
        setupAdapter()
        val userPreference = UserPreference(this)
        viewModel.getListProductsRepo(userPreference.getUser().token.toString(),1,100,"","")

        viewModel.getListProducts().observe(this) {
            if (it != null) {
                productList.addAll(it)
            }
        }

        binding.btnLogout.setOnClickListener { logout() }
        binding.btnAddProduct.setOnClickListener {
            val intent = Intent(this@BerandaActivity, AddProductActivity::class.java)
            startActivity(intent)
        }
        binding.btnBack.setOnClickListener { logout() }
        binding.edtSearch.setOnClickListener {
            viewModel.getListProductsRepo(userPreference.getUser().token.toString(),
                1,100,binding.edtSearch.text.toString(),"")
        }
    }

    private fun setupAdapter() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvProduct.layoutManager = layoutManager
        productAdapter = ProductAdapter(productList, this)
        binding.rvProduct.adapter = productAdapter
        if (productList.size > 0){
            binding.btnAddProduct.isVisible = false
            binding.tvProduct.isVisible = false
        }
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
            val userPreference = UserPreference(this)
            userPreference.clearUser()
            finish()
        }
        dialogBinding.buttonNo.setOnClickListener { dialog.dismiss() }
    }

    private fun connectViewModel(){
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ProductViewModel::class.java]
    }
}