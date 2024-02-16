package com.example.getemployeedatafromapi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.getemployeedatafromapi.Interface.EmployeeApi
import com.example.getemployeedatafromapi.R

class SelectedEmpDetailsActivity : AppCompatActivity() {

    lateinit var txtId: TextView
    lateinit var employeeName: TextView
    lateinit var employeeSalary: TextView
    lateinit var employeeAge: TextView

    lateinit var employee: EmployeeApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_emp_details)

        initview()
    }

    private fun initview() {

//        employee = RetrofitHelperClass.getInstance().create(Employee::class.java)
//
//        txtId = findViewById(R.id.txtId)
//        employeeName = findViewById(R.id.employeeName)
//        employeeSalary = findViewById(R.id.employeeSalary)
//        employeeAge = findViewById(R.id.employeeAge)
//
//        GlobalScope.launch {
//
//            var id = intent.getIntExtra("id",-1)
//
//            var result = employee.getEmployeeDetail()
//
//            var list : List<EmployeeDetailsResponseItem>? = result.body()
//
//            var selectedProduct = list?.find { it.id == id }
//
//            runOnUiThread{
//
//                if(selectedProduct != null)
//                {
//                    txtId.text = selectedProduct.id.toString()
//                    employeeName.text = selectedProduct.employeeName
//                    employeeSalary.text = selectedProduct.employeeSalary.toString()
//                    employeeAge.text = selectedProduct.employeeAge.toString()
//
//                }
//
//            }
//
//        }

    }
}