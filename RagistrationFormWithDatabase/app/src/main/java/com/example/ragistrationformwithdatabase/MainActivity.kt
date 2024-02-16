package com.example.ragistrationformwithdatabase

import DatabaseHelper
import DisplayModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseHelper
    lateinit var edtName : EditText
    lateinit var edtAge : EditText
    lateinit var edtAddress : EditText
    lateinit var edtEmail : EditText
    lateinit var edtMobileNo : EditText
    lateinit var edtCourse : EditText
    lateinit var btnSubmit : Button
    lateinit var btnDisplay : Button
    lateinit var rcvDisplayData : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview() {

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtAddress = findViewById(R.id.edtAddress)
        edtEmail = findViewById(R.id.edtEmail)
        edtMobileNo = findViewById(R.id.edtMobileNo)
        edtCourse = findViewById(R.id.edtCourse)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnDisplay = findViewById(R.id.btnDisplay)
        rcvDisplayData = findViewById(R.id.rcvDisplayData)

        database = DatabaseHelper(this,"Admin.db",null,1)

        btnSubmit.setOnClickListener {

            var name = edtName.text.toString()
            var age = edtAge.text.toString()
            var address = edtAddress.text.toString()
            var email = edtEmail.text.toString()
            var mobileno = edtMobileNo.text.toString()
            var course = edtMobileNo.text.toString()

            if(name.isEmpty())
            {
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show()
            }
            else if(age.isEmpty())
            {
                Toast.makeText(this,"Please enter your age",Toast.LENGTH_SHORT).show()
            }
            else if(address.isEmpty())
            {
                Toast.makeText(this,"Please enter your address",Toast.LENGTH_SHORT).show()
            }
            else if(email.isEmpty())
            {
                Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show()
            }
            else if(mobileno.isEmpty())
            {
                Toast.makeText(this,"Please enter your mobileno",Toast.LENGTH_SHORT).show()
            }
            else if(course.isEmpty())
            {
                Toast.makeText(this,"Please enter your course name",Toast.LENGTH_SHORT).show()
            }
            else
            {
                database.insertData(name,age,address,email,mobileno,course)
            }
        }

        btnDisplay.setOnClickListener {


            var list = ArrayList<DisplayModel>()

                list = database.display()




        }

    }

}