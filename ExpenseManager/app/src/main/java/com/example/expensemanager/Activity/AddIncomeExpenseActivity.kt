package com.example.expensemanager.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.example.expensemanager.Adapter.CategoryAdapter
import com.example.expensemanager.Adapter.ModeAdpter
import com.example.expensemanager.HelperClass.DatabaseHelper
import com.example.expensemanager.Model.ModalClass
import com.example.expensemanager.Model.ModeModalclass
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityAddIncomeExpenseBinding
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale

class AddIncomeExpenseActivity : AppCompatActivity() {

//    binding
    lateinit var binding: ActivityAddIncomeExpenseBinding

    var Categorylist = ArrayList<ModalClass>()
    var Modelist = ArrayList<ModeModalclass>()

    lateinit var database: DatabaseHelper

    lateinit var category : String
    lateinit var Mode : String

//    var category = arrayOf<String?>("Business", "Clothing","Drinks","Education","Food","Fuel","Fun","Hospital","Hotel","Loan",
//        "Medical", "Merchandise","Movie","Other","Personal","Pets","Restaurant","Salary","Shopping",
//        "Tips", "Transport")
//
//    var Mode = arrayOf<String?>("cash","cheque",
//        "Credit Card","Debit Card",
//        "Net Banking")

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddIncomeExpenseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // setContentView(R.layout.activity_add_income_expense)

        database = DatabaseHelper(this, "ExpenseManagerDb", null, 1)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }

    private fun initview() {


        binding.imgArrow.setOnClickListener {

            onBackPressed()

        }

//        getdata from intent
        if(intent != null)
        {
            var getdata = intent.getStringExtra("AddIncomeExpense")

            if(getdata == "0")
            {
                binding.rbIncome.isChecked = true
                binding.txtText.text = "AddIncome"
            }
            else
            {
                binding.rbExpense.isChecked = true
                binding.txtText.text = "AddExpense"
            }
        }


        //spinner
//       spinner.onItemSelectedListener = this
//        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
//            this,
//            android.R.layout.simple_spinner_item,category)
//        ad.setDropDownViewResource(
//            android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = ad
//
//
//        //spinner2
//        spinnerMode.onItemSelectedListener = this
//        val add: ArrayAdapter<*> = ArrayAdapter<Any?>(
//            this,
//            android.R.layout.simple_spinner_item,Mode)
//        ad.setDropDownViewResource(
//            android.R.layout.simple_spinner_dropdown_item)
//        spinnerMode.adapter = add


        //  for calander view
        val myCalendar = Calendar.getInstance()
        val datapicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofmonth ->

            myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.YEAR, year)
            updateLabal(myCalendar)

        }

        binding.txtDate.setOnClickListener {

            DatePickerDialog(
                this, datapicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        //time picker
        binding.txtTime.setOnClickListener {

            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = calendar.get(Calendar.MINUTE)

            val textView: TextView = findViewById(R.id.txtTime)


            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", currentHour, minute)
                    textView.text = selectedTime
                },
                currentHour,
                currentMinute,
                false
            )

            timePickerDialog.show()

        }


        Categorylist = database.DisplayCategory()

        var adapter = CategoryAdapter(this, Categorylist)
        binding.spinner.adapter = adapter

        Modelist = database.DisplayMode()

        var Modeadapter = ModeAdpter(this, Modelist)
        binding.spinnerMode.adapter = Modeadapter

        //Add Category
        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                category = Categorylist[position].Category
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        //Add Payment Mode
        binding.spinnerMode.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                Mode = Modelist[position].name

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        //insert data
        binding.imgDone.setOnClickListener {

            var amount =  binding.edtAmount.text.toString()
            // val category = txtitemCategories.text.toString()
            var date = binding.txtDate.text.toString()
            //   val mode = txtMode.text.toString()
            var note = binding.edtNote.text.toString()
            var time = binding.txtTime.text.toString()

            if (amount.isEmpty())
            {
                Toast.makeText(this, "Please Enter Your Amount ", Toast.LENGTH_SHORT).show()
            }
            else if (category.isEmpty())
            {
                Toast.makeText(this, "Please Enter Your Category ", Toast.LENGTH_SHORT).show()
            }
            else if (date.isEmpty()) {

                Toast.makeText(this, "Please Enter Your Date  ", Toast.LENGTH_SHORT).show()
            }
            else if (time.isEmpty())
            {

                Toast.makeText(this, "Please Enter Your time  ", Toast.LENGTH_SHORT).show()
            }
            else if (Mode.isEmpty())
            {
                Toast.makeText(this, "Please Enter Your Mode ", Toast.LENGTH_SHORT).show()
            }
            else if (note.isEmpty())
            {
                Toast.makeText(this, "Please Enter note ", Toast.LENGTH_SHORT).show()
            }
            else {

                var getdata = intent.getStringExtra("AddIncomeExpense")

                if(getdata == "0")
                {
                    database.InsertIncomeData(amount,category,date,Mode, note)
                }
                else
                {
                    database.InsertExpenseData(amount,category,date,Mode, note)
                }

               // Toast.makeText(this, "Your Data Add SuccesFully", Toast.LENGTH_SHORT).show()

                intent = Intent(this@AddIncomeExpenseActivity, AllTransactionActivity::class.java)
                startActivity(intent)

                onBackPressed()

            }
        }

    }

    //date format
    private fun updateLabal(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.txtDate.setText(sdf.format(myCalendar.time))
    }

}

