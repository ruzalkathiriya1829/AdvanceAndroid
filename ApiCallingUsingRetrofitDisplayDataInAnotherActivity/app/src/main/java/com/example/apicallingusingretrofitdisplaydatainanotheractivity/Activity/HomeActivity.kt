package com.example.apicallingusingretrofitdisplaydatainanotheractivity.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.apicallingusingretrofitdisplaydatainanotheractivity.R

class HomeActivity : AppCompatActivity() {

    lateinit var LinearAddProduct: LinearLayout
    lateinit var LinearApiCallUsingGET: LinearLayout
    lateinit var LinearGetDataFromCart: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initview()
    }

    private fun initview() {

        LinearApiCallUsingGET = findViewById(R.id.LinearApiCallUsingGET)
        LinearAddProduct = findViewById(R.id.LinearAddProduct)
        LinearGetDataFromCart = findViewById(R.id.LinearGetDataFromCart)

        LinearApiCallUsingGET.setOnClickListener {

            intent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(intent)

        }

        LinearAddProduct.setOnClickListener {

            intent = Intent(this@HomeActivity, AddProductActivity::class.java)
            startActivity(intent)

        }

        LinearGetDataFromCart.setOnClickListener {

            intent = Intent(this@HomeActivity, CallCartApiActivity::class.java)
            startActivity(intent)

        }

    }
}