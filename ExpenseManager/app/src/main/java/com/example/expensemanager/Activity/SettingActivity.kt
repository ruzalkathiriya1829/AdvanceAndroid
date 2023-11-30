package com.example.expensemanager.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityAppLockBinding
import com.example.expensemanager.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivitySettingBinding
    lateinit var spinnerCurrency: Spinner
    lateinit var spinnerDate: Spinner
    lateinit var spinnerTime: Spinner

    var spinCurrency = arrayOf<String?>("AED", "AFN", "Lek", "AMD", "f", "AOA",
        "$","$", "f","AZN", "KM", "$", "BDT", "BGN", "BIF", "$","$", "$", "BOV", "r$", "$", "BTC", "BTN", "P", "P.", "BZ$", "$", "CDF", "CHF",
        "CHF",
        "CLF",
        "$",
        "CNY",
        "$",
        "COU",
        "CRC",
        "UC$",
        "CUP",
        "CVE",
        "CYP",
        "CZK",
        "DJF",
        "kr",
        "RD$",
        "DZD",
        "kr",
        "ERN",
        "ETB",
        "$",
        "GEL",
        "GMD",
        "GNF",
        "Q",
        "GWP",
        "L",
        "kn",
        "HTG",
        "Ft",
        "Rp",
        "ILS",
        "Rs."
    )

    var spinDate = arrayOf<String?>("MM.dd.yy","dd.MM.yy","yy.MM.dd","MM.dd.yyyy","dd.MM.yyyy","yyyy.MM.dd","MM-dd-yy","dd-MM-yy","yy-MM-dd",
                "MM-dd-yyyy","dd-MM-yyyy","yyyy-MM-dd","MM/dd/yy","dd/MM/yy","yy/MM/dd","MM/dd/yyyy","dd/MM/yyyy","yyyy/MM/dd","MMM dd yyyy",
                "dd MMM yyyy","yyyy MMM dd","MMMM dd yyyy","dd MMMM yyyy","yyyy MMMM dd","EEE MMMM dd yyyy","EEEEEE MMMM dd yyyy","MMM dd, yyyy",
                "dd MMM,yyyy","MMMM dd,yyyy","dd MMMM,yyyy","EEE,MMMM dd,yyyy","EEEEEE,MMMM dd,yyyy")

    var spinTime = arrayOf<String?>("hh:mm a","HH:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
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

        //currency
        binding.spinnerCurrency.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,spinCurrency)
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCurrency.adapter = ad

        //date
        binding.spinnerDate.onItemSelectedListener = this

        val ad1: ArrayAdapter<*> = ArrayAdapter<Any?>(
             this,
            android.R.layout.simple_spinner_item,spinDate)
        ad1.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDate.adapter = ad1

        //time
        binding.spinnerTime.onItemSelectedListener = this

        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,spinTime)
        ad2.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTime.adapter = ad2

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}