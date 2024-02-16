package com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass

import com.google.gson.annotations.SerializedName

data class ProductDetailModel(

	@field:SerializedName("ProductDetailModelClass")
	val productDetailModelClass: List<ProductDetailModelClassItem?>? = null
)

data class Rating(

	@field:SerializedName("rate")
	val rate: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class ProductDetailModelClassItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
