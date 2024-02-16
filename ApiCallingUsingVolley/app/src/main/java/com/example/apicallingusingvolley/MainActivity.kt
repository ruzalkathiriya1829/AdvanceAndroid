package com.example.apicallingusingvolley

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import javax.xml.transform.ErrorListener


class MainActivity : AppCompatActivity() {

    private var mRequestQueue: RequestQueue? = null
    private var mStringRequest: StringRequest? = null
    private val url = "https://api.github.com/users/zellwk/repos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview() {

        mRequestQueue = Volley.newRequestQueue(this)


        mStringRequest = StringRequest(Request.Method.GET, url, object : Response.Listener<String?> {
            // display the response on screen


            override fun onResponse(response: String?) {
                Toast.makeText(applicationContext, "Response :$response", Toast.LENGTH_LONG)
                    .show()

                Log.e("TAG", "onResponse: success "+response )
            }
        }, object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("TAG", "onErrorResponse: "+error?.message )
            }

        })
        mRequestQueue!!.add(mStringRequest)
     
    }
}