package com.example.technical_test_farouq.data.retrofit

import com.example.onlinestorefarouq.data.response.ResponseGeneral
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponseGeneral>

    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponseGeneral>
}