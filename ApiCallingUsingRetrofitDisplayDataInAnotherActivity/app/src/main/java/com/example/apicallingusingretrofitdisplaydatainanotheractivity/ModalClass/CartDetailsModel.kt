package com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass

import com.google.gson.annotations.SerializedName

data class CartDetailsModel(

	@field:SerializedName("CartDetailsModel")
	val cartDetailsModel: List<CartDetailsModelItem?>? = null
)

data class ProductsItem(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("productId")
	val productId: Int? = null
)

data class CartDetailsModelItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

