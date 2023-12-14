package com.example.onlinestorefarouq.repository.data.response

data class ResponseListProduct(
	val code: String? = null,
	val data: Data? = null,
	val message: String? = null
)

data class Data(
	val nextPage: Any? = null,
	val perPage: Int? = null,
	val total: Int? = null,
	val lastPage: String? = null,
	val items: List<ItemsItem?>? = null,
	val prevPage: Any? = null
)

data class ItemsItem(
	val image: String? = null,
	val price: Int? = null,
	val description: String? = null,
	val id: Int? = null,
	val variants: List<VariantsItem?>? = null,
	val title: String? = null,
	val totalVariant: Int? = null,
	val totalStok: Int? = null
)

data class VariantsItem(
	val image: String? = null,
	val price: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val stock: Int? = null
)

