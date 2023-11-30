package com.example.expensemanager.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expensemanager.HelperClass.DatabaseHelper
import com.example.expensemanager.R

class AddCategoryActivity : AppCompatActivity() {

    lateinit var database: DatabaseHelper
    lateinit var edtCategory: EditText
    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        database = DatabaseHelper(this,"ExpenseManagerDb",null,1)


        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {


        edtCategory = findViewById(R.id.edtCategory)
        btnAdd = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {

            var Addcategory = edtCategory.text.toString()

            if (Addcategory.isEmpty()) {
                Toast.makeText(this, "Please enter category ", Toast.LENGTH_SHORT).show()
            } else {
                database.insertCategory(Addcategory)
                onBackPressed()
            }
        }

    }
}