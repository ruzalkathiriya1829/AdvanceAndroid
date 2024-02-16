package com.example.alarmservices

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    var alarmTimePicker: TimePicker? = null
    lateinit var pendingIntent: PendingIntent
    var alarmManger: AlarmManager? = null

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmTimePicker = findViewById(R.id.timepicker)
        alarmManger = getSystemService(ALARM_SERVICE) as AlarmManager

    }


    fun onToggleClicked(view: android.view.View) {
        var time: Long
        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this, "Alarm ON", Toast.LENGTH_SHORT).show()

            var calender = Calendar.getInstance()

            calender[Calendar.HOUR_OF_DAY] = alarmTimePicker!!.currentHour
            calender[Calendar.MINUTE] = alarmTimePicker!!.currentMinute

            val intent = Intent(this, AlarmReceiver::class.java)

            pendingIntent = PendingIntent.getBroadcast(
                this,

                0, intent, PendingIntent.FLAG_MUTABLE
            )
            time = calender.timeInMillis - calender.timeInMillis % 60000

            if (System.currentTimeMillis() > time) {
                time = if (Calendar.AM_PM == 0) {
                    time + 1000 * 60 * 60 * 12

                } else {
                    time + 1000 * 60 * 60 * 24
                }
            }
            alarmManger!!.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000, pendingIntent)
        } else {
            alarmManger!!.cancel(pendingIntent)
            Toast.makeText(this, "Alarm OFF", Toast.LENGTH_SHORT).show()
        }
    }
}