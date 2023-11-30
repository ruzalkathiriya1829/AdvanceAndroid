package com.example.expensemanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityPreminumBinding

class PreminumActivity : AppCompatActivity() {
    lateinit var binding: ActivityPreminumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreminumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {

        binding.imgClose.setOnClickListener {

            onBackPressed()
        }

    }
}