package com.example.technical_test_farouq.data.retrofit

import com.example.onlinestorefarouq.repository.data.request.LoginRequest
import com.example.onlinestorefarouq.repository.data.request.RegisterRequest
import com.example.onlinestorefarouq.repository.data.response.LoginResponse
import com.example.onlinestorefarouq.repository.data.response.ResponseGeneral
import com.example.onlinestorefarouq.repository.data.response.ResponseListProduct
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    fun login(
        @Body loginRequest: LoginRequest,
    ): Call<LoginResponse>

    @POST("auth/register")
    fun register(
        @Body registerRequest: RegisterRequest,
    ): Call<ResponseGeneral>

    @GET("product")
    fun getListProduct(
        @Header("Authorization") token: String,
        @Query("page") page: Int?,
        @Query("per page") per_page: Int?,
        @Query("search") search: String?,
        @Query("order_direction") order_direction: String,
        ): Call<ResponseListProduct>

}