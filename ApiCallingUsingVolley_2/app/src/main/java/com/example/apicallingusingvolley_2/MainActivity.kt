package com.example.apicallingusingvolley_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnClick: Button
    var url = "https://api.github.com/users/zellwk/repos"
    private var ApiRequest: RequestQueue? = null
    lateinit var txtData: TextView
    lateinit var avatar: String
    lateinit var img: ImageView
    lateinit var arrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview() {

        btnClick = findViewById(R.id.btnClick)
        txtData = findViewById(R.id.txtData)
        img = findViewById(R.id.img)

//        //simple
//        btnClick.setOnClickListener{
//
//            ApiRequest = Volley.newRequestQueue(this)
//
//            var StringRequest = StringRequest(Request.Method.GET,url,object : Response.Listener<String>{
//                override fun onResponse(response: String?) {
//                    txtData.text = response
//                }
//
//            },
//               object :Response.ErrorListener{
//                   override fun onErrorResponse(error: VolleyError?) {
//                       Log.e("TAG", "onErrorResponse: "+ error )
//                   }
//
//               })
//
//            ApiRequest!!.add(StringRequest)
//        }

        //using arraylist

        btnClick.setOnClickListener {

            ApiRequest = Volley.newRequestQueue(this)

            var apirequest = JsonObjectRequest(Request.Method.GET, url, null, {
                val DataArray = it.getJSONArray("data")

                for (i in 0 until DataArray.length()) {

                    val dataobject = DataArray.getJSONObject(i)
                    var id = dataobject.getInt("id")
                    var full_name = dataobject.getString("full_name")
                    var login = dataobject.getString("login")
                    avatar = dataobject.getString("avatar_url")

                    txtData.append("$id,$full_name,$login")

                }

                Glide.with(this).load(avatar).into(img)
            }, {
                Log.e("TAG", "error: " + it)

            })

            ApiRequest!!.add(apirequest)
        }


    }
}