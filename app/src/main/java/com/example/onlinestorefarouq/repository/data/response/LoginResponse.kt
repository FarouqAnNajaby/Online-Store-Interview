package com.example.onlinestorefarouq.repository.data.response

data class LoginResponse(
	val code: String? = null,
	val data: Data? = null,
	val message: String? = null
)

data class Data(
	val id: Int? = null,
	val token: String? = null
)

