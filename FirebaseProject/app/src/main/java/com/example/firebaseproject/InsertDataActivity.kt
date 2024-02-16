package com.example.firebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class InsertDataActivity : AppCompatActivity() {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var edtname: EditText
    lateinit var edtPhone: EditText
    lateinit var edtAddress: EditText
    lateinit var btnInsert: Button
    var empId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)


        initView()
    }

    private fun initView() {
        edtname = findViewById(R.id.idEdtEmployeeName)
        edtPhone = findViewById(R.id.idEdtEmployeePhoneNumber)
        edtAddress = findViewById(R.id.idEdtEmployeeAddress)
        btnInsert = findViewById(R.id.idBtnSendData)

        firebaseDatabase = FirebaseDatabase.getInstance();
        

        btnInsert.setOnClickListener {

y
            if (empId.isEmpty()) {
                var key = firebaseDatabase.reference.root.child("Employee").push().key ?: ""

                var eemp = Employee(key, "test", "23456789o", "sdfghjk")
                firebaseDatabase.reference.root.child("Employee").child(key).setValue(eemp)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "data addeddd successfully", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                var eemp = Employee(empId, "deevvvv", "gjhres54tytdrfyr5", "fgfhgfhnb")
                firebaseDatabase.reference.root.child("Employee").child(empId).setValue(eemp)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "data updated successfully", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }


        }


        if (intent.extras != null) {
            empId = intent.extras?.getString("id", "") ?: ""
            var name = intent.extras?.getString("name", "")
            var phone = intent.extras?.getString("phone", "")
            var add = intent.extras?.getString("add", "")



            edtAddress.setText(add)
            edtname.setText(name)
            edtPhone.setText(phone)

        }


    }
}

class Employee {
    var empId: String = ""
    var name: String = ""
    var Phone: String = ""
    var address: String = ""

    constructor(empId: String, name: String, Phone: String, address: String) {
        this.empId = empId
        this.name = name
        this.Phone = Phone
        this.address = address

    }

    constructor() {

    }
}
