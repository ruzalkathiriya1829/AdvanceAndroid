package com.example.firebaseprojectdemo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseprojectdemo.PushNotification.NotificationActivity
import com.example.firebaseprojectdemo.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        binding.LinearEmployee.setOnClickListener {

            intent = Intent(this@DashboardActivity, EmployeeDataFromActivity::class.java)
            startActivity(intent)
        }

        binding.LinearStudent.setOnClickListener {

            intent = Intent(this@DashboardActivity, StudentDataFromActivity::class.java)
            startActivity(intent)
        }

        binding.LinearEmployeeDisplay.setOnClickListener {

            intent = Intent(this@DashboardActivity, DisplayEmployeeDataActivity::class.java)
            startActivity(intent)
        }

        binding.LinearStudentDisplay.setOnClickListener {

            intent = Intent(this@DashboardActivity, DisplayStudentDataActivity::class.java)
            startActivity(intent)
        }

        binding.LinearPushNotification.setOnClickListener {

            intent = Intent(this@DashboardActivity, NotificationActivity::class.java)
            startActivity(intent)
        }
    }
}