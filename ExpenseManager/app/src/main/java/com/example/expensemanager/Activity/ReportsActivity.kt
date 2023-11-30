package com.example.expensemanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.R

class ReportsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)
    }
}