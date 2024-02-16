package com.example.firebaseself.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseself.Adapter.RecyclerviewAdapter
import com.example.firebaseself.ModalClass.DataModal
import com.example.firebaseself.databinding.ActivityRecyclerviewWithFirebaseBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlin.math.log


class RecyclerviewWithFirebase : AppCompatActivity() {

    lateinit var binding: ActivityRecyclerviewWithFirebaseBinding
    lateinit var firebaseDatabase: FirebaseDatabase
//    lateinit var adapter: RecyclerviewAdapter
//    lateinit var storage: FirebaseStorage
//    lateinit var storageReference: StorageReference
    lateinit var dataModal: DataModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewWithFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        // get the Firebase  storage reference
//        storage = FirebaseStorage.getInstance()
//        storageReference = storage.getReference()

        firebaseDatabase = FirebaseDatabase.getInstance()


        binding.btnTxt.setOnClickListener {

                var key = firebaseDatabase.reference.root.child("StudentTb").push().key ?: ""

                var id = binding.edtId.text.toString()
                var name = binding.edtName.text.toString()
                var coursename = binding.edtCourseName.text.toString()
                var age = binding.edtAge.text.toString()

                dataModal = DataModal(key, id, name, coursename, age)

                firebaseDatabase.reference.root.child("StudentTb").child(key).setValue(dataModal)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            Toast.makeText(this@RecyclerviewWithFirebase, "Data Insert Successfully", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }.addOnFailureListener {
                    Toast.makeText(this@RecyclerviewWithFirebase, it.message, Toast.LENGTH_SHORT).show()
                }
        }

//        val options = FirebaseRecyclerOptions.Builder<DataModal>()
//            .setQuery(FirebaseDatabase.getInstance().reference.child("persons"), DataModal::class.java)
//            .build()
//
//        adapter = RecyclerviewAdapter(options)
//        binding.rcvData.adapter = adapter
    }

//    override fun onStart() {
//        super.onStart()
//        adapter.startListening()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        adapter.stopListening()
//    }
}