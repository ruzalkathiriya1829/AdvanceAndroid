package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Adapter.DisplayAdpter
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.Interface.ProductApi
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var rcv: RecyclerView
    lateinit var productApi: ProductApi
    lateinit var displayAdpter: DisplayAdpter
//    lateinit var btnClick: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initview() {

        productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)

        rcv = findViewById(R.id.rcv)
//        btnClick = findViewById(R.id.btnClick)

//        btnClick.setOnClickListener {

        GlobalScope.launch {

            val result = productApi.getProductDetail()
           // Log.e("TAG", "initview:======> " + result.body().toString())

            if (result != null) {

                runOnUiThread {


                    var manager =
                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

                    displayAdpter = DisplayAdpter(this@MainActivity, result.body(), data = { id,position->

                          //Log.d("TAG", "Data=====>: "+id)

                        intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)

                    })

                    rcv.adapter = displayAdpter
                    rcv.layoutManager = manager

                }
            }
        }
    }
}