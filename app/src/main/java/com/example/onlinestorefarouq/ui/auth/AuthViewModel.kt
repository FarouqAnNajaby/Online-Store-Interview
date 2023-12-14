package com.example.onlinestorefarouq.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestorefarouq.repository.data.model.User
import com.example.onlinestorefarouq.repository.data.request.LoginRequest
import com.example.onlinestorefarouq.repository.data.request.RegisterRequest
import com.example.onlinestorefarouq.repository.data.response.LoginResponse
import com.example.onlinestorefarouq.repository.data.response.ResponseGeneral
import com.example.technical_test_farouq.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val status = MutableLiveData<String>()
    private val token = MutableLiveData<String>()


    fun status(): LiveData<String> {
        return status
    }

    fun getToken(): LiveData<String> {
        return token
    }

    fun loginRepo(loginRequest: LoginRequest){
        val data = ApiConfig.getApiService().login(loginRequest)
        data.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    status.postValue(response.body()?.message.toString())
                    token.postValue(response.body()?.data?.token.toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
                status.postValue(t.message.toString())
            }

        })
    }

    fun registerRepo(registerRequest: RegisterRequest){
        val data = ApiConfig.getApiService().register(registerRequest)
        data.enqueue(object: Callback<ResponseGeneral> {
            override fun onResponse(call: Call<ResponseGeneral>, response: Response<ResponseGeneral>) {
                if (response.isSuccessful){
                    status.postValue(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<ResponseGeneral>, t: Throwable) {
                Log.d("Error", t.message.toString())
                status.postValue(t.message.toString())
            }

        })
    }

}