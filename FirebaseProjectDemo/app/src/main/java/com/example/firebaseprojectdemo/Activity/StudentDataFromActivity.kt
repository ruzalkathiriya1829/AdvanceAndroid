package com.example.firebaseprojectdemo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.firebaseprojectdemo.ModalClass.StudentModal
import com.example.firebaseprojectdemo.databinding.ActivityStudentDataFromBinding
import com.google.firebase.database.FirebaseDatabase

class StudentDataFromActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentDataFromBinding
    lateinit var firebaseDatabase : FirebaseDatabase
    lateinit var studentModal: StudentModal
    var StudId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDataFromBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        firebaseDatabase = FirebaseDatabase.getInstance()

//        var rbId = binding.rbMaleFemale.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup, i ->  })

        binding.btnInsertStudData.setText("Insert Data")

        binding.btnInsertStudData.setOnClickListener {

            if(StudId.isEmpty())
            {
                var key = firebaseDatabase.reference.root.child("StudentTb").push().key ?: ""

                var Grid = binding.edtStudGrid.text.toString()
                var name = binding.edtStudName.text.toString()
                var phone = binding.edtStudPhone.text.toString()
                var email = binding.edtStudEmail.text.toString()
                var course = binding.edtStudCourse.text.toString()
                var fees = binding.edtStudFees.text.toString()
                var rbmale = binding.rbMale.text.toString()
                var rbfemale = binding.rbFemale.text.toString()
                var cbcooking = binding.cbCooking.text.toString()
                var cbtravelling = binding.cbTraveling.text.toString()
                var cbsinging = binding.cbSinging.text.toString()


                studentModal = StudentModal(key,Grid,name,phone,email,course,fees,rbmale,rbfemale,cbcooking,cbtravelling,cbsinging)

                firebaseDatabase.reference.root.child("StudentTb").child(key).setValue(studentModal).addOnCompleteListener {

                    if(it.isSuccessful)
                    {
                        Toast.makeText(this@StudentDataFromActivity, "Data Insert Successfully", Toast.LENGTH_SHORT).show()

                        intent = Intent(this@StudentDataFromActivity,DisplayStudentDataActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener {
                    Toast.makeText(this@StudentDataFromActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                var studgrid = binding.edtStudGrid.text.toString()
                var studname = binding.edtStudName.text.toString()
                var studphone = binding.edtStudCourse.text.toString()
                var studemail = binding.edtStudEmail.text.toString()
                var studcourse = binding.edtStudCourse.text.toString()
                var studfees = binding.edtStudFees.text.toString()
                var rbMale = binding.rbMale.text.toString()
                var rbFemale = binding.rbFemale.text.toString()
                var cbcooking = binding.cbCooking.text.toString()
                var cbtravelling = binding.cbTraveling.text.toString()
                var cbsinging = binding.cbSinging.text.toString()

                studentModal = StudentModal(StudId,studgrid,studname,studphone,studemail,studcourse,studfees,rbMale,rbFemale,cbcooking,cbtravelling,cbsinging)

                firebaseDatabase.reference.root.child("StudentTb").child(StudId).setValue(studentModal).addOnCompleteListener {

                    if(it.isSuccessful)
                    {
                        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()

                        intent = Intent(this@StudentDataFromActivity,DisplayStudentDataActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener {

                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }

        //get data from click edit
        if(intent.extras != null)
        {
            StudId = intent.extras?.getString("StudId","") ?: ""
            var StudGRID = intent.extras?.getString("StudGRID","")
            var StudName = intent.extras?.getString("StudName","")
            var StudPhone = intent.extras?.getString("StudPhone","")
            var StudEmail = intent.extras?.getString("StudEmail","")
            var StudCourse = intent.extras?.getString("StudCourse","")
            var StudFees = intent.extras?.getString("StudFees","")
            var rbMale = intent.extras?.getString("rbMale","")
            var rbFemale = intent.extras?.getString("rbFemale","")
            var cbCooking = intent.extras?.getString("cbCooking","")
            var cbTraveling = intent.extras?.getString("cbTraveling","")
            var cbSinging = intent.extras?.getString("cbSinging","")

            binding.edtStudGrid.setText(StudGRID)
            binding.edtStudName.setText(StudName)
            binding.edtStudPhone.setText(StudPhone)
            binding.edtStudEmail.setText(StudEmail)
            binding.edtStudCourse.setText(StudCourse)
            binding.edtStudFees.setText(StudFees)
            binding.rbMale.setText(rbMale)
            binding.rbFemale.setText(rbFemale)
            binding.cbCooking.setText(cbCooking)
            binding.cbTraveling.setText(cbTraveling)
            binding.cbSinging.setText(cbSinging)

            //set text on button
            binding.btnInsertStudData.setText("Update Data")
        }

    }
}