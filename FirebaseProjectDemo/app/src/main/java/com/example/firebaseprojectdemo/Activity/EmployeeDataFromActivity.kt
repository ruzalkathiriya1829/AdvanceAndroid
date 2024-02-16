package com.example.firebaseprojectdemo.Activity

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseprojectdemo.ModalClass.EmployeeModal
import com.example.firebaseprojectdemo.R
import com.example.firebaseprojectdemo.databinding.ActivityEmployeeDataFromBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.util.UUID


class EmployeeDataFromActivity : AppCompatActivity() {


    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var binding: ActivityEmployeeDataFromBinding
    lateinit var firebaseDatabase : FirebaseDatabase
    lateinit var employeeModal: EmployeeModal
    var empId = ""
    lateinit var filePath: Uri
    lateinit var txtCamera : TextView
    lateinit var txtGallery : TextView
    private val PICK_IMAGE_REQUEST = 22
    private val pic_id = 100
    lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDataFromBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = Dialog(this@EmployeeDataFromActivity)

        initview()
    }

    private fun initview() {


        // get the Firebase  storage reference
         storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()



        firebaseDatabase = FirebaseDatabase.getInstance()

        binding.btnTxt.setText("Insert Data")

        binding.btnTxt.setOnClickListener {

            //check id is empty or not if empty than data insert and not empty than data in update
            if(empId.isEmpty())
            {

                var key = firebaseDatabase.reference.root.child("EmployeeTb").push().key ?: ""

                var name = binding.edtEmpName.text.toString()
                var phone = binding.edtEmpPhone.text.toString()
                var email = binding.edtEmpEmail.text.toString()
                var address = binding.edtEmpAddress.text.toString()
                var salary = binding.edtEmpSalary.text.toString()

                employeeModal = EmployeeModal(key,name,phone,email,address,salary)

                firebaseDatabase.reference.root.child("EmployeeTb").child(key).setValue(employeeModal).addOnCompleteListener {

                    if(it.isSuccessful)
                    {
                        Toast.makeText(this@EmployeeDataFromActivity, "Data Insert Successfully", Toast.LENGTH_SHORT).show()

                        intent = Intent(this@EmployeeDataFromActivity,DisplayEmployeeDataActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener {
                    Toast.makeText(this@EmployeeDataFromActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }

            else
            {

                var name = binding.edtEmpName.text.toString()
                var phone = binding.edtEmpPhone.text.toString()
                var email = binding.edtEmpEmail.text.toString()
                var address = binding.edtEmpAddress.text.toString()
                var salary = binding.edtEmpSalary.text.toString()

                employeeModal = EmployeeModal(empId,name,phone,email,address,salary)

                firebaseDatabase.reference.root.child("EmployeeTb").child(empId).setValue(employeeModal).addOnCompleteListener {

                        if(it.isSuccessful)
                        {
                            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()

                            intent = Intent(this@EmployeeDataFromActivity,DisplayEmployeeDataActivity::class.java)
                            startActivity(intent)
                        }
                }.addOnFailureListener{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }


        }



        //get data from edit click
        if(intent.extras != null)
        {
            empId = intent.extras?.getString("empId","") ?: ""
            var empName = intent.extras?.getString("empName","")
            var empAddress = intent.extras?.getString("empAddress","")
            var empPhone = intent.extras?.getString("empPhone","")
            var empSalary = intent.extras?.getString("empSalary","")
            var empEmail = intent.extras?.getString("empEmail","")

            binding.edtEmpName.setText(empName)
            binding.edtEmpAddress.setText(empAddress)
            binding.edtEmpPhone.setText(empPhone)
            binding.edtEmpSalary.setText(empSalary)
            binding.edtEmpEmail.setText(empEmail)


            //button text
            binding.btnTxt.setText("Update Data")
        }


        //select image from gallery

        binding.imgSelect.setOnClickListener {

            selectImage()
        }

        //upload image on firebase

        binding.btnUpload.setOnClickListener {

            uploadImage()
        }


    }

    private fun uploadImage() {

        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref: StorageReference = storageReference
                .child(
                    "images/"
                            + UUID.randomUUID().toString()
                )

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                .addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast.makeText(this, "Image Uploaded!!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            this,
                            "Failed " + e.message,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->

                    // Progress Listener for loading
                    // percentage on the dialog box
                    val progress = (100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded "
                                + progress.toInt() + "%"
                    )
                }
        }
    }

    private fun selectImage() {

        dialog.setContentView(R.layout.dialog_layout)
        dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        txtCamera = dialog.findViewById(R.id.txtCamera)
        txtGallery = dialog.findViewById(R.id.txtGallery)

        txtCamera.setOnClickListener{
            dialog.dismiss()

            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(camera_intent,pic_id )
        }

        txtGallery.setOnClickListener{
            dialog.dismiss()

            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Image from here..."
                ),
                PICK_IMAGE_REQUEST
            )
        }

        dialog.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // then set image in the image view

            if ((requestCode == PICK_IMAGE_REQUEST) && (resultCode == RESULT_OK) && (data != null) && (data.getData() != null)) {

                // Get the Uri of data
                filePath = data?.getData()!!
                try {

                    // Setting image on image view using Bitmap
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(
                            contentResolver,
                            filePath
                        )
                    binding.imgSelect.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    // Log the exception
                    e.printStackTrace()
                }
            }

        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
//            val photo = data!!.extras!!["data"] as Bitmap?
//            // Set the image in imageview for display
////            binding.imgSelect.setImageBitmap(photo)
//
//            val extra: Bundle = data.getExtras()!!
//            val photos = extra["data"] as Bitmap?
//            val path: String = getOriginalImagePath() ?: ""
//            val f = File(path) //
//
//            var ImageURI = Uri.fromFile(f)
//            binding.imgSelect.setImageURI(ImageURI)
        }
    }

//    fun getOriginalImagePath(): String? {
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor: Cursor = this.managedQuery(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            projection, null, null, null
//        )
//        val column_index_data = cursor
//            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToLast()
//        return cursor.getString(column_index_data)
//    }
}