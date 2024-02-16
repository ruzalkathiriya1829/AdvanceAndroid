package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Interface.ProductApi
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.ProductDetailModel
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.ProductDetailModelClassItem
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {

    lateinit var txtid: TextView
    lateinit var txttitle: TextView
    lateinit var txtprice: TextView
    lateinit var txtdescription: TextView
    lateinit var txtcategory: TextView
    lateinit var txtrating: TextView
    lateinit var img: ImageView

    lateinit var productApi: ProductApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initview()
    }

    private fun initview() {

        productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)

        txtid = findViewById(R.id.txtid)
        txttitle = findViewById(R.id.txttitle)
        txtprice = findViewById(R.id.txtprice)
        txtdescription = findViewById(R.id.txtdescription)
        txtcategory = findViewById(R.id.txtcategory)
        txtrating = findViewById(R.id.txtrating)
        img = findViewById(R.id.img)

        GlobalScope.launch {

            var id = intent.getIntExtra("id",-1)

            var result = productApi.getProductDetail()

            var list : List<ProductDetailModelClassItem>? = result.body()

            var selectedProduct = list?.find { it.id == id }

            runOnUiThread{

                if(selectedProduct != null)
                {
                        txtid.text = selectedProduct.id.toString()
                        txttitle.text = selectedProduct.title
                        txtprice.text = selectedProduct.price.toString()
                        txtdescription.text = selectedProduct.description
                        txtcategory.text = selectedProduct.category
                        txtrating.text = selectedProduct.rating.toString()
                        Glide.with(img).load(selectedProduct.image).into(img)

                }

            }

        }
    }
}