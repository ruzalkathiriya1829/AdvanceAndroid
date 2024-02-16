package com.example.getemployeedatafromapi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getemployeedatafromapi.Adapter.DisplayEmployeeAdpter
import com.example.getemployeedatafromapi.Interface.EmployeeApi
import com.example.getemployeedatafromapi.Model.EmployeeDetailsResponse
import com.example.getemployeedatafromapi.R
import com.example.getemployeedatafromapi.RetrofitHelperClass
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AllEmployeeDataActivity : AppCompatActivity() {


    lateinit var rcvEmpData: RecyclerView
    lateinit var employeeApi: EmployeeApi
    lateinit var btnClick: Button
    lateinit var displayEmployeeAdpter: DisplayEmployeeAdpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_employee_data)

        initview()
    }


    private fun initview() {

        employeeApi = RetrofitHelperClass.getInstance().create(EmployeeApi::class.java)

        btnClick = findViewById(R.id.btnClick)
        rcvEmpData = findViewById(R.id.rcvEmpData)

        btnClick.setOnClickListener {

            GlobalScope.launch {

                val result = employeeApi.getEmployeeDetail()

                if(result != null)

                    Log.d("TAG", "initview:==> " + result.body().toString())

                    var data: EmployeeDetailsResponse? = result.body()
                    Log.e("TAG", "initview:~~~ " + data?.data?.get(0)?.id )

                    runOnUiThread {

                        var manager =
                            LinearLayoutManager(this@AllEmployeeDataActivity, LinearLayoutManager.VERTICAL,false)

                        displayEmployeeAdpter = DisplayEmployeeAdpter(this@AllEmployeeDataActivity,result.body()?.data)

                        rcvEmpData.adapter = displayEmployeeAdpter
                        rcvEmpData.layoutManager = manager
                    }

            }
        }

    }
}