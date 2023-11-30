package com.example.expensemanager.Activity

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.Adapter.DisplayAdapter
import com.example.expensemanager.HelperClass.DatabaseHelper
import com.example.expensemanager.Model.DisplayModalClass
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityAllTransactionBinding
import kotlinx.coroutines.Delay


class AllTransactionActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllTransactionBinding
    lateinit var Displayadpter : DisplayAdapter
    lateinit var database: DatabaseHelper

    var Displaylist = ArrayList<DisplayModalClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAllTransactionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //setContentView(R.layout.activity_all_transaction)

        database = DatabaseHelper(this, "ExpenseManagerDb", null,1)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {


        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        Displaylist =  database.DisplayAllTransactionTableData()


        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        Displayadpter = DisplayAdapter(this,Displaylist)
        binding.rcvAllTransaction.adapter = Displayadpter
        binding.rcvAllTransaction.layoutManager = manager

    }

    override fun onResume()
    {
        super.onResume()
        Displaylist = database.DisplayAllTransactionTableData()
        Displayadpter.refresh(Displaylist)

    }


}