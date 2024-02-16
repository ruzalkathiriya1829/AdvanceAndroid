package com.example.rterofitproject


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface QuotesApi {

    @GET("/quotes")
     suspend fun getQuotes() : Response<QuoteList>



    @GET("/products/{id}")
    suspend fun getProductDetail(@Path("id")  id : Int) : Response<ProdductDetailModel>


    @GET("/carts")
    suspend fun getCartData(@Query("userId")  id : Int) : Response<ProdductDetailModel>

    
    //post
    @POST("/products")
    suspend fun addProduct(@Body model: AddProductDetailModel): Response<AddnewProductResponse>


    @FormUrlEncoded
    @POST("/products")
    suspend fun addProductOther(@Field("title") title : String, @Field("price") price : Int, @Field("description") description :String, @Field("image") image : String, @Field("category") category : String): Response<AddnewProductResponse>

}