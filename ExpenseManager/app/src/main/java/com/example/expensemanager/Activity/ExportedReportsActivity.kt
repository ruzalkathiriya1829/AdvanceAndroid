package com.example.expensemanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityExportedReportsBinding
import com.example.expensemanager.databinding.ActivitySettingBinding

class ExportedReportsActivity : AppCompatActivity() {

    lateinit var binding: ActivityExportedReportsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportedReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {

        binding.imgBack.setOnClickListener {
            intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}