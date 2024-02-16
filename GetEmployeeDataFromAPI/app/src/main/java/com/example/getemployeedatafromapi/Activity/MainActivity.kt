package com.example.getemployeedatafromapi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getemployeedatafromapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        binding.LinearEmployeeWholeData.setOnClickListener {

            intent = Intent(this@MainActivity, AllEmployeeDataActivity::class.java)
            startActivity(intent)
        }

//        binding.LinearCreateNewEmp.setOnClickListener {
//
//
//        }

    }
}