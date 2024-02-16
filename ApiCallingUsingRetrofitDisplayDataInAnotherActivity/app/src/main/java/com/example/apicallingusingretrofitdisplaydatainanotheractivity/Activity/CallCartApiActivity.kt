package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Adapter.DisplayCartDataAdpter
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Interface.ProductApi
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CallCartApiActivity : AppCompatActivity() {

    lateinit var rcvCallApiCart : RecyclerView
    lateinit var productApi: ProductApi
    lateinit var displayCartDataAdpter: DisplayCartDataAdpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_cart_api)

        initview()
    }

    private fun initview() {

        rcvCallApiCart = findViewById(R.id.rcvCallApiCart)

        productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)

        GlobalScope.launch {

            val result = productApi.getCartData(userId = 1 )

            if(result != null)
            {
//                var data = result.body()

                runOnUiThread {

                   var manager = LinearLayoutManager(this@CallCartApiActivity,LinearLayoutManager.VERTICAL,false)
                    displayCartDataAdpter = DisplayCartDataAdpter(this@CallCartApiActivity,result.body())
                    rcvCallApiCart.adapter = displayCartDataAdpter
                    rcvCallApiCart.layoutManager = manager
                }
            }
        }

    }
}
