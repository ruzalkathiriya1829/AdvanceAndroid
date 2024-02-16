package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.String

class DisplayActivity : AppCompatActivity() {

    var empList: ArrayList<Employee> = ArrayList()
    lateinit var rcvEmployee: RecyclerView


    lateinit var firebaseDatabase: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)



        initView()


    }

    private fun initView() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        rcvEmployee = findViewById(R.id.rcvEmployee)

        firebaseDatabase.reference.root.child("Employee")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    empList.clear()
                    for (data in snapshot.getChildren()) {
                        val emp = data.getValue(Employee::class.java)
                        emp?.let { empList.add(it) }

                    }

                    var adapter = EmployeeAdapter(empList, edtClick = { id, name, phone, add ->

                        var i = Intent(this@DisplayActivity, InsertDataActivity::class.java)
                        i.putExtra("id", id)
                        i.putExtra("name", name)
                        i.putExtra("phone", phone)
                        i.putExtra("add", add)
                        startActivity(i)


                    }, deleteClick = { id ->
                        firebaseDatabase.reference.root.child("Employee").child(id).removeValue()
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(
                                        this@DisplayActivity,
                                        "delete success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }.addOnFailureListener {
                            Toast.makeText(this@DisplayActivity, "failure", Toast.LENGTH_SHORT)
                                .show()
                        }


                    })
                    var manager = LinearLayoutManager(
                        this@DisplayActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    rcvEmployee.layoutManager = manager
                    rcvEmployee.adapter = adapter

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "onCancelled: " + error.message)
                }

            })
    }
}