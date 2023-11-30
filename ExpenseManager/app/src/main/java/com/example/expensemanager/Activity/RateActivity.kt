package com.example.expensemanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityRateBinding

class RateActivity : AppCompatActivity() {

    lateinit var  binding: ActivityRateBinding

//    lateinit var ratingbar : RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()

    }

    private fun initview() {

        binding.btnSubmit.setOnClickListener {

            if(binding.ratingbar != null)
            {
                val button = findViewById<AppCompatButton>(R.id.btnSubmit)
                button.setOnClickListener {

                    Toast.makeText(this, "Rate is SuccesFully Upload!!", Toast.LENGTH_SHORT).show()
                    intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)

                }
            }
        }

    }
}