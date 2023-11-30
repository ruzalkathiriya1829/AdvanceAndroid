package com.example.expensemanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {


    lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {

        binding.Calenderview.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->

                val Date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)
                binding.txtDate.setText(Date)
            })

    }
}