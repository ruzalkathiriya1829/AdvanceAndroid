package com.example.firebaseprojectdemo.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseprojectdemo.Adapter.DisplayEmployeeAdapter
import com.example.firebaseprojectdemo.ModalClass.EmployeeModal
import com.example.firebaseprojectdemo.databinding.ActivityDisplayEmployeeDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DisplayEmployeeDataActivity : AppCompatActivity() {

    lateinit var binding : ActivityDisplayEmployeeDataBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var displayEmployeeAdpter: DisplayEmployeeAdapter
    var empList : ArrayList<EmployeeModal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayEmployeeDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseDatabase.reference.root.child("EmployeeTb").addValueEventListener(object : ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {

                empList.clear()

                for(data in snapshot.getChildren()){
                    val emp = data.getValue(EmployeeModal::class.java)

                    emp?.let { empList.add(it) }
                }

                displayEmployeeAdpter = DisplayEmployeeAdapter(empList, EditClick = {empId, empName, empPhone, empEmail, empAddress, empSalary ->

                    intent = Intent(this@DisplayEmployeeDataActivity,EmployeeDataFromActivity::class.java)
                    intent.putExtra("empId",empId)
                    intent.putExtra("empName",empName)
                    intent.putExtra("empPhone",empPhone)
                    intent.putExtra("empEmail",empEmail)
                    intent.putExtra("empAddress",empAddress)
                    intent.putExtra("empSalary",empSalary)
                    startActivity(intent)
                }, DeleteClick = {empId ->

                    //alert dialog box
                    val builder = androidx.appcompat.app.AlertDialog.Builder(this@DisplayEmployeeDataActivity)

                        builder.setMessage("Are You Sure to Delete Record ?")
                        builder.setTitle("Delete Record !")
                        builder.setCancelable(false)

                        builder.setPositiveButton("Yes"){
                                dialog,which -> finish()

                            //delete data from database
                            firebaseDatabase.reference.root.child("EmployeeTb").child(empId).removeValue().addOnCompleteListener {

                                if(it.isSuccessful)
                                {
                                    Toast.makeText(this@DisplayEmployeeDataActivity, "Data Delete SuccessFully", Toast.LENGTH_SHORT).show()
                                }

                            }.addOnFailureListener {
                                Toast.makeText(this@DisplayEmployeeDataActivity, it.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        builder.setNegativeButton("No"){
                                dialog,which -> dialog.cancel()
                        }

                        val alertDialog = builder.create()
                        alertDialog.show()

//                        firebaseDatabase.reference.root.child("EmployeeTb").child(empId).removeValue().addOnCompleteListener {
//
//                            if(it.isSuccessful)
//                            {
//                                Toast.makeText(this@DisplayEmployeeDataActivity, "Data Delete SuccessFully", Toast.LENGTH_SHORT).show()
//                            }
//
//                        }.addOnFailureListener {
//                            Toast.makeText(this@DisplayEmployeeDataActivity, it.message, Toast.LENGTH_SHORT).show()
//                        }
                })


                var manager = LinearLayoutManager(this@DisplayEmployeeDataActivity, LinearLayoutManager.VERTICAL, false)
                binding.rcvEmpData.adapter = displayEmployeeAdpter
                binding.rcvEmpData.layoutManager = manager

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "onCancelled: "+error.message)
            }


        })
    }



//    fun AlertDialogBox(){
//        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
//
//        builder.setMessage("Are You Sure to Delete Record ?")
//        builder.setTitle("Delete Record !")
//        builder.setCancelable(false)
//
//        builder.setPositiveButton("Yes"){
//                dialog,which -> finish()
//        }
//
//        builder.setNegativeButton("No"){
//                dialog,which -> dialog.cancel()
//        }
//
//        val alertDialog = builder.create()
//        alertDialog.show()
//
//    }

}