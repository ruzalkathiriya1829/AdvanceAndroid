package com.example.quetesapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quetesapp.Adapter.displayAdapter
import com.example.quetesapp.Model.displayModel
import com.example.quetesapp.MyDatabase
import com.example.quetesapp.R
import com.example.quetesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //binding
    lateinit var binding: ActivityMainBinding

    lateinit var adpter : displayAdapter
    lateinit var database: MyDatabase

    //image array
    var quotesImage = arrayOf<Int>(R.drawable.happy,R.drawable.emotion,R.drawable.love,
                        R.drawable.success,R.drawable.brithday,R.drawable.friend,R.drawable.life,
                        R.drawable.book,R.drawable.dream,R.drawable.music,R.drawable.teacher,R.drawable.mother,
                        R.drawable.father,R.drawable.broken,R.drawable.flower,R.drawable.funny,R.drawable.leadership,
                        R.drawable.cousins,R.drawable.god,R.drawable.gratitude,R.drawable.women,R.drawable.inspirational,
                         R.drawable.reading, R.drawable.possesive,R.drawable.strenth)

    var list = ArrayList<displayModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.black)

        initview()
    }

    private fun initview() {

        database = MyDatabase(this)
        list = database.DisplayData()

        binding.imgHeart.setOnClickListener {

            intent = Intent(this@MainActivity,LikeQuotesActivity::class.java)
            startActivity(intent)
        }

        adpter = displayAdapter(this,list,quotesImage, onclick = {id, name->

            intent = Intent(this@MainActivity,QuotesActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("id",id)
            startActivity(intent)

           // Toast.makeText(this, "name is ==> "+name, Toast.LENGTH_SHORT).show()

        })

        var manager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        binding.rcvCategory.adapter = adpter    
        binding.rcvCategory.layoutManager = manager

    }
}