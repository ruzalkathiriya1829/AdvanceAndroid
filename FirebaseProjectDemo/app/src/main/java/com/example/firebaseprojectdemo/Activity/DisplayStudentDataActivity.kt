package com.example.firebaseprojectdemo.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseprojectdemo.Adapter.DisplayStudentAdapter
import com.example.firebaseprojectdemo.ModalClass.StudentModal
import com.example.firebaseprojectdemo.R
import com.example.firebaseprojectdemo.databinding.ActivityDisplayStudentDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class DisplayStudentDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityDisplayStudentDataBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var displayStudentAdapter: DisplayStudentAdapter
    var studList : ArrayList<StudentModal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayStudentDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseDatabase.reference.root.child("StudentTb").addValueEventListener(object : ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {

                studList.clear()

                for(data in snapshot.getChildren()){
                    val stud = data.getValue(StudentModal::class.java)

                        stud?.let { studList.add(it) }

                }

                displayStudentAdapter  = DisplayStudentAdapter(studList, ClickEdit = {StudId, StudGRID, StudName, StudPhone, StudEmail, StudCourse, StudFees, rbMale, rbFemale, cbCooking, cbTravelling, cbSinging ->

                    intent = Intent(this@DisplayStudentDataActivity,StudentDataFromActivity::class.java)
                    intent.putExtra("StudId",StudId)
                    intent.putExtra("StudGRID",StudGRID)
                    intent.putExtra("StudName",StudName)
                    intent.putExtra("StudPhone",StudPhone)
                    intent.putExtra("StudEmail",StudEmail)
                    intent.putExtra("StudCourse",StudCourse)
                    intent.putExtra("StudFees",StudFees)
                    intent.putExtra("rbMale",rbMale)
                    intent.putExtra("rbFemale",rbFemale)
                    intent.putExtra("cbCooking",cbCooking)
                    intent.putExtra("cbTravelling",cbTravelling)
                    intent.putExtra("cbSinging",cbSinging)
                    startActivity(intent)
                }, ClickDelete = { StudId ->

                    //alert dialog box
                        val builder = androidx.appcompat.app.AlertDialog.Builder(this@DisplayStudentDataActivity)

                        builder.setMessage("Are You Sure to Delete Record ?")
                        builder.setTitle("Delete Record !")
                        builder.setCancelable(false)

                        builder.setPositiveButton("Yes"){
                                dialog,which -> finish()

                            //delete data from database
                            firebaseDatabase.reference.root.child("StudentTb").child(StudId).removeValue().addOnCompleteListener {

                                Toast.makeText(this@DisplayStudentDataActivity, "Data delete successfully", Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {

                                Toast.makeText(this@DisplayStudentDataActivity, it.message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        builder.setNegativeButton("No"){
                                dialog,which -> dialog.cancel()
                        }

                        val alertDialog = builder.create()
                        alertDialog.show()

//                        firebaseDatabase.reference.root.child("StudentTb").child(StudGRID).removeValue().addOnCompleteListener {
//
//                            Toast.makeText(this@DisplayStudentDataActivity, "Data delete successfully", Toast.LENGTH_SHORT).show()
//                        }.addOnFailureListener {
//
//                            Toast.makeText(this@DisplayStudentDataActivity, it.message, Toast.LENGTH_SHORT).show()
//                        }
                    })

                var manager = LinearLayoutManager(this@DisplayStudentDataActivity,LinearLayoutManager.VERTICAL,false)
                binding.rcvStudData.adapter = displayStudentAdapter
                binding.rcvStudData.layoutManager = manager
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "onCancelled: "+error.message )
            }


        })


    }
}