package com.example.firebaseproject

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.UUID


class UploadImageActivity : AppCompatActivity() {


    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var filePath: Uri
    lateinit var btnUploadImage : Button
    lateinit var btnSelectImage : Button
    lateinit var imgView : ImageView
    private val PICK_IMAGE_REQUEST = 22


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        initView()
    }

    private fun initView() {

        btnUploadImage=findViewById(R.id.btnUploadimage)
        btnSelectImage=findViewById(R.id.btnSelectImage)
        imgView=findViewById(R.id.imgView)


        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();




        btnSelectImage.setOnClickListener {
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


        btnUploadImage.setOnClickListener {
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
                    Toast
                        .makeText(
                            this,
                            "Image Uploaded!!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // then set image in the image view
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData()!!
            try {

                // Setting image on image view using Bitmap
                val bitmap = MediaStore.Images.Media
                    .getBitmap(
                        contentResolver,
                        filePath
                    )
                imgView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace()
            }
        }
    }

}