package com.example.firebaseself.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.firebaseself.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {


        binding.btnNext.setOnClickListener {

            intent = Intent(this@MainActivity, FacebookLoginUsingFirebase::class.java)
            startActivity(intent)
        }

        val firebaseDatabase = FirebaseDatabase.getInstance()

        val databaseReference: DatabaseReference = firebaseDatabase.getReference()

        val getImage = databaseReference.child("Image")

        getImage.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // getting a DataSnapshot for the
                    // location at the specified relative
                    // path and getting in the link variable
                    val link = dataSnapshot.getValue(String::class.java)

                    Log.e("TAG", "onDataChange:====> "+link )
                    // loading that data into rImage
                    // variable which is ImageView
                    Glide.with(this@MainActivity).load(link).into(binding.rImage)
                }

                // this will called when any problem
                // occurs in getting data
                override fun onCancelled(databaseError: DatabaseError) {
                    // we are showing that error message in
                    // toast
                    Toast
                        .makeText(this@MainActivity, "Error Loading Image", Toast.LENGTH_SHORT).show()
                }
            })

    }
}