package com.example.quetesapp.Activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quetesapp.Adapter.LikeAdapter
import com.example.quetesapp.Model.LikeQuoteModel
import com.example.quetesapp.MyDatabase
import com.example.quetesapp.R
import com.example.quetesapp.databinding.ActivityLikeQuotesBinding
import com.example.quetesapp.databinding.ActivityQuotesBinding

class LikeQuotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityLikeQuotesBinding

    var favouritelist = ArrayList<LikeQuoteModel>()
    lateinit var favouriteadapter: LikeAdapter
    lateinit var databse: MyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikeQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.black)

        databse = MyDatabase(this)

        initview()
    }

    private fun initview() {

        binding.imgBack.setOnClickListener {
            onBackPressed()

        }


        favouritelist = databse.LikeQuoteDisplay()


        favouriteadapter = LikeAdapter(this, favouritelist, like =  { status, id ->
            databse.UpdateLikeQuotes(status, id)
        },

            OnCopy = { id, Quotes ->

                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Quetos", Quotes)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Copy on clipboard ", Toast.LENGTH_SHORT).show()


            },

            Share = { id, Quetos ->

                val dataToShare = Quetos
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, dataToShare)
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            } ,

            Edit = { id, Quetos ->

                intent = Intent(this, EditActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("Quetos", Quetos)
                startActivity(intent)
            })


        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = favouriteadapter
        binding.rcv.layoutManager = manager
    }
}