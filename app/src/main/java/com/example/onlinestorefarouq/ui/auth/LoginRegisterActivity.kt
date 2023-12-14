package com.example.onlinestorefarouq.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.onlinestorefarouq.R
import com.example.onlinestorefarouq.databinding.ActivityLoginRegisterBinding
import com.example.onlinestorefarouq.helper.Constanta
import com.example.onlinestorefarouq.repository.data.UserPreference
import com.example.onlinestorefarouq.repository.data.model.User
import com.example.onlinestorefarouq.repository.data.request.LoginRequest
import com.example.onlinestorefarouq.repository.data.request.RegisterRequest
import com.example.onlinestorefarouq.ui.beranda.BerandaActivity


class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding
    private var isShow: Boolean = false
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavbarAndButton()
        connectViewModel()

        binding.btnBack.setOnClickListener { finish() }
        binding.btnIconEye.setOnClickListener {
            isShow = !isShow
            if (isShow) {
                binding.edtPass.transformationMethod = PasswordTransformationMethod()
                binding.btnIconEye.setImageResource(R.drawable.icon_close_eye);
            } else {
                binding.edtPass.transformationMethod = null
                binding.btnIconEye.setImageResource(R.drawable.icon_open_eye);
            }
        }
        binding.btnSubmit.setOnClickListener { validationForm() }
    }

    private fun validationForm() {
        if (intent.getStringExtra(Constanta.LABEL) == Constanta.IS_REGISTER)
            validationRegister()
        else
            validationLogin()
    }

    private fun validationLogin() {
        if (!isValidEmail(binding.edtEmail.text.toString())){
            Toast.makeText(this, R.string.email_empty, Toast.LENGTH_SHORT).show()
        }
        else if (!isValidPassword(binding.edtPass.text.toString())){
            Toast.makeText(this, R.string.password_empty, Toast.LENGTH_SHORT).show()
        }
        else{
            val data = LoginRequest(
                binding.edtEmail.text.toString(),
                binding.edtPass.text.toString(),
            )
            viewModel.loginRepo(data)
            binding.loadingView.isVisible = true
        }
        viewModel.status().observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                binding.loadingView.isVisible = false
            }
        }
        viewModel.getToken().observe(this) {
            if (it != null) {
                saveUser(binding.edtEmail.text.toString(),binding.edtPass.text.toString(),it,
                    binding.edtName.text.toString())
                val i = Intent(this@LoginRegisterActivity, BerandaActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    private fun validationRegister() {
        if (binding.edtName.text.isNullOrEmpty()){
            Toast.makeText(this, R.string.name_empty, Toast.LENGTH_SHORT).show()
        }
        else if (!isValidEmail(binding.edtEmail.text.toString())){
            Toast.makeText(this, R.string.email_empty, Toast.LENGTH_SHORT).show()
        }
        else if (!isValidPassword(binding.edtPass.text.toString())){
            Toast.makeText(this, R.string.password_empty, Toast.LENGTH_SHORT).show()
        }
        else{
            val data = RegisterRequest(
                binding.edtName.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtPass.text.toString(),
            )
            viewModel.registerRepo(data)
            binding.loadingView.isVisible = true
        }

        viewModel.status().observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                binding.loadingView.isVisible = false
            }
        }

    }

    private fun setNavbarAndButton() {

        if (intent.getStringExtra(Constanta.LABEL) == Constanta.IS_REGISTER){
            binding.textHeader.setText(R.string.register)
            binding.btnSubmit.setText(R.string.register)
        }
        else{
            binding.textHeader.setText(R.string.login)
            binding.btnSubmit.setText(R.string.login)
            binding.edtName.isVisible = false
            binding.tvName.isVisible = false
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 6) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }

    private fun connectViewModel(){
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[AuthViewModel::class.java]
    }

    private fun saveUser(email: String,password: String,token: String,name:String) {
        val dataUser = User(
            email,
            name,
            password,
            token
        )
        val userPreference = UserPreference(this)
        userPreference.setUser(dataUser)
    }

}