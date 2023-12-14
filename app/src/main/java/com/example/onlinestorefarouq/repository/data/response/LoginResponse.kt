package com.example.onlinestorefarouq.repository.data.response

data class LoginResponse(
	val code: String? = null,
	val data: DataLogin? = null,
	val message: String? = null
)

data class DataLogin(
	val id: Int? = null,
	val token: String? = null
)

