package com.example.rterofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var btnApi: Button
    lateinit var quotesApi: QuotesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
    }

    private fun initView() {

        quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        btnApi = findViewById(R.id.btnApi)

        btnApi.setOnClickListener {

//            GlobalScope.launch {
//                val result = quotesApi.getQuotes()
//
//                if (result != null)
//                // Checking the results
//                    Log.e("ayush: ", result.body().toString())
//
//                var data : QuoteList? =result.body()
//                Log.e("TAG", "initView: "+data?.results?.get(0)?.id )
//            }


            GlobalScope.launch {

                //raw
//                var model = AddProductDetailModel(
//                    "test product",
//                    220,
//                    "test descritpion",
//                    "876545tyhfjdhgdfhdm",
//                    "cloths"
//                )
//               var result = quotesApi.addProduct(model)


                // form data
               var result = quotesApi.addProductOther("fghjkl",3,"bnm","jhghj","ughj")

                Log.e("TAG", "initView: ===========> "+result.body().toString())
            }


        }
    }




}