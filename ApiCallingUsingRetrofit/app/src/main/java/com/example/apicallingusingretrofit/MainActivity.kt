package com.example.apicallingusingretrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var quotesApi: QuotesApi
    lateinit var btnClick: Button
    lateinit var rcvData: RecyclerView
    lateinit var displayDataAdapter: DisplayDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview() {

        quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        btnClick = findViewById(R.id.btnClick)
        rcvData = findViewById(R.id.rcvData)


        btnClick.setOnClickListener {


            //for chaking internet connection

            if (checkForInternet(this)) {
                Toast.makeText(this, "Internet Is connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT)
                    .show()
            }

            // launching a new coroutine
            GlobalScope.launch {
                val result = quotesApi.getQuotes()


                if (result != null)

                // Checking the results
                    Log.d("initView: ", result.body().toString())


                var data: QuoteList? = result.body()
                Log.e("TAG", "initView: " + data?.results?.get(0)?.id)


//                if (data != null) {
//                    Displaylist.add(data)
//                }



            // display code in forground
                    runOnUiThread {
                    // Stuff that updates the UI

                    var manager =
                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

                    displayDataAdapter = DisplayDataAdapter(this@MainActivity, result.body()?.results)
                    // displayDataAdapter = DisplayDataAdapter(this@MainActivity,Displaylist)

                    rcvData.adapter = displayDataAdapter
                    rcvData.layoutManager = manager
                }

            }

        }

    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
