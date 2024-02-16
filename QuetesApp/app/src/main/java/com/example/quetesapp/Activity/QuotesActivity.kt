package com.example.quetesapp.Activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quetesapp.Adapter.displayQuotesAdapter
import com.example.quetesapp.Model.displayQuotesModel
import com.example.quetesapp.MyDatabase
import com.example.quetesapp.R
import com.example.quetesapp.databinding.ActivityQuotesBinding

class QuotesActivity : AppCompatActivity() {

    var id: Int = 0
    lateinit var name: String
    private lateinit var binding: ActivityQuotesBinding
    lateinit var database: MyDatabase
    lateinit var displayadapter: displayQuotesAdapter

    var quoteslist = ArrayList<displayQuotesModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.black)

        database = MyDatabase(this)

        initview()
    }

    private fun initview() {

        if (intent != null) {

            id = intent.getIntExtra("id", id)
            name = intent.getStringExtra("name").toString()
            binding.txt.text = name

        }


        if (id == 1) {
            quoteslist = database.DisplayQuotes(1)
        } else if (id == 2) {
            quoteslist = database.DisplayQuotes(2)
        } else if (id == 3) {
            quoteslist = database.DisplayQuotes(3)
        } else if (id == 4) {
            quoteslist = database.DisplayQuotes(4)
        } else if (id == 5) {
            quoteslist = database.DisplayQuotes(5)
        } else if (id == 6) {
            quoteslist = database.DisplayQuotes(6)
        } else if (id == 7) {
            quoteslist = database.DisplayQuotes(7)
        } else if (id == 8) {
            quoteslist = database.DisplayQuotes(8)
        } else if (id == 9) {
            quoteslist = database.DisplayQuotes(9)
        } else if (id == 10) {
            quoteslist = database.DisplayQuotes(10)
        } else if (id == 11) {
            quoteslist = database.DisplayQuotes(11)
        } else if (id == 12) {
            quoteslist = database.DisplayQuotes(12)
        } else if (id == 13) {
            quoteslist = database.DisplayQuotes(13)
        } else if (id == 14) {
            quoteslist = database.DisplayQuotes(14)
        } else if (id == 15) {
            quoteslist = database.DisplayQuotes(15)
        } else if (id == 16) {
            quoteslist = database.DisplayQuotes(16)
        } else if (id == 17) {
            quoteslist = database.DisplayQuotes(17)
        } else if (id == 18) {
            quoteslist = database.DisplayQuotes(18)
        } else if (id == 19) {
            quoteslist = database.DisplayQuotes(19)
        } else if (id == 20) {
            quoteslist = database.DisplayQuotes(20)
        } else if (id == 21) {
            quoteslist = database.DisplayQuotes(21)
        } else if (id == 22) {
            quoteslist = database.DisplayQuotes(22)
        } else if (id == 23) {
            quoteslist = database.DisplayQuotes(23)
        } else if (id == 24) {
            quoteslist = database.DisplayQuotes(24)
        } else if (id == 25) {
            quoteslist = database.DisplayQuotes(25)
        }


        displayadapter = displayQuotesAdapter(this, quoteslist, Edit = { id, Quetos ->

            intent = Intent(this, EditActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("Quetos", Quetos)
            intent.putExtra("name", name)
            startActivity(intent)
        },

            click = {id, Quetos ->

                intent = Intent(this@QuotesActivity,EditActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("Quetos", Quetos)
                intent.putExtra("name", name)
                startActivity(intent)

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

            Like = { LikeStatus ,id  ->
                database.UpdateLikeQuotes(LikeStatus,id.toInt())
            })



        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvQuotes.adapter = displayadapter
        binding.rcvQuotes.layoutManager = manager

    }
}