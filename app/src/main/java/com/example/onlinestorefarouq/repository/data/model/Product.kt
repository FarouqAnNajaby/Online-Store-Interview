package com.example.onlinestorefarouq.repository.data.model

import com.example.onlinestorefarouq.repository.data.response.VariantsItem

data class Product (
    val image: String? = null,
    val price: Int? = null,
    val description: String? = null,
    val id: Int? = null,
    val variants: List<VariantsItem?>? = null,
    val title: String? = null,
    val totalVariant: Int? = null,
    val totalStok: Int? = null
)