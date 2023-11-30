package com.example.expensemanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expensemanager.HelperClass.DatabaseHelper
import com.example.expensemanager.R

class AddPaymentActivity : AppCompatActivity() {

    lateinit var database: DatabaseHelper
    lateinit var edtPayment: EditText
    lateinit var btnAddPayment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)

        database = DatabaseHelper(this,"ExpenseManagerDb",null,1)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()

    }

    private fun initview() {

        edtPayment = findViewById(R.id.edtPayment)
        btnAddPayment = findViewById(R.id.btnAddPayment)

        btnAddPayment.setOnClickListener {

            var Addpayment = edtPayment.text.toString()

            if(Addpayment.isEmpty())
            {
                Toast.makeText(this,"Please enter payment Mode",Toast.LENGTH_SHORT).show()
            }
            else
            {
                database.insertPaymentMode(Addpayment)
                onBackPressed()
            }
        }

    }
}