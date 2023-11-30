package com.example.expensemanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.expensemanager.HelperClass.DatabaseHelper
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityRagistrationBinding

class RagistrationActivity : AppCompatActivity() {

    lateinit var database: DatabaseHelper
    lateinit var LoginNext : LinearLayout
    lateinit var RegisterBtn : Button
    lateinit var edtFullName : EditText
    lateinit var edtEmail : EditText
    lateinit var edtUserName : EditText
    lateinit var edtPassword : EditText


    private lateinit var binding: ActivityRagistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragistration)

        database = DatabaseHelper(this,"ExpenseManagerDb",null,1)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {

//        binding = ActivityRagistrationBinding.inflate(layoutInflater)
//        setContentView(binding.root)


        LoginNext = findViewById(R.id.LoginNext)
        RegisterBtn = findViewById(R.id.RegisterBtn)
        edtFullName = findViewById(R.id.edtFullName)
        edtEmail = findViewById(R.id.edtEmail)
        edtUserName = findViewById(R.id.edtUserName)
        edtPassword = findViewById(R.id.edtPassword)

        RegisterBtn.setOnClickListener {

            var name = edtFullName.text.toString()
            var email = edtEmail.text.toString()
            var username = edtUserName.text.toString()
            var password = edtPassword.text.toString()

            if(name.isEmpty())
            {
                Toast.makeText(this, "Please enter your full name",Toast.LENGTH_SHORT).show()
            }
            else if (email.isEmpty())
            {
                Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show()
            }
            else if (username.isEmpty())
            {
                Toast.makeText(this,"Please Enter Username",Toast.LENGTH_SHORT).show()
            }
            else if (password.isEmpty())
            {
                Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show()
            }
            else
            {
                database.insertData(name, email, username, password)

                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        LoginNext.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}