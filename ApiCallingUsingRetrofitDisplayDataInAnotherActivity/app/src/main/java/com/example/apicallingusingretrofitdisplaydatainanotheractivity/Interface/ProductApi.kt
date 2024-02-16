package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Interface

import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.AddNewProductResponse
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.AddProductDetailsModel
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.CartDetailsModelItem
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.ProductDetailModelClassItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/products")
    suspend fun getProductDetail(): Response<List<ProductDetailModelClassItem>>

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<List<ProductDetailModelClassItem>>

    @GET("/carts")
    suspend fun getCartData(@Query("userId") userId: Int): Response<List<CartDetailsModelItem>>


    //Post
    @POST("/products")
    suspend fun addProduct(@Body model: AddProductDetailsModel): Response<AddNewProductResponse>

    @FormUrlEncoded
    @POST("/products")
    suspend fun addProductData(@Field("title") title : String, @Field("price") price : Int, @Field("description") description : String, @Field("image") image : String, @Field("category") category : String) :Response<AddProductDetailsModel>


}