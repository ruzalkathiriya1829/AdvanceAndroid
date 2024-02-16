package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Interface.ProductApi
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.ModalClass.AddProductDetailsModel
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class AddProductActivity : AppCompatActivity() {

    lateinit var edttitle: EditText
    lateinit var edtprice: EditText
    lateinit var edtdescription: EditText
    lateinit var edtimage: EditText
    lateinit var edtcategory: EditText
    lateinit var btnSubmit: Button
    lateinit var productApi: ProductApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        initview()
    }

    private fun initview() {

        productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)

        btnSubmit = findViewById(R.id.btnSubmit)
        edttitle = findViewById(R.id.edttitle)
        edtprice = findViewById(R.id.edtprice)
        edtdescription = findViewById(R.id.edtdescription)
        edtimage = findViewById(R.id.edtimage)
        edtcategory = findViewById(R.id.edtcategory)

        btnSubmit.setOnClickListener {

            GlobalScope.launch {

                //raw

                var model = AddProductDetailsModel(
                    "waesrdtfgyhujji",
                    12,
                    "qwertyujhngfbdfvscfasdf",
                    "qewsrdtghg",
                    "ewfrg"
                )

                var result = productApi.addProduct(model)

                Log.d("TAG", "initview: ===>" + result)
            }
//
//            GlobalScope.launch {
//
//                //from data
//                var result = productApi.addProductData("fghjkl", 3, "bnm", "jhghj", "ughj")
//
//                Log.d("TAG", "initview: ======>" + result.body().toString())
//            }
//
        }
    }
}