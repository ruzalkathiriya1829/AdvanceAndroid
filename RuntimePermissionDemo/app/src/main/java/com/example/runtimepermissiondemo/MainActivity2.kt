package com.example.runtimepermissiondemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.runtimepermissiondemo.databinding.ActivityMain2Binding
import com.example.runtimepermissiondemo.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    private var imageUri: Uri? = null

    companion object {
        // Define the pic id
        private const val pic_id = 123
        private const val pic_img = 100
        //       const val CAMERA_PERMISSION_CODE = 100
//         const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {


        binding.btnStorage.setOnClickListener {
            checkPermissionForStorage(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                MainActivity.STORAGE_PERMISSION_CODE
            )
        }

        binding.btnCamera.setOnClickListener {
            checkPermissionForCamera(
                Manifest.permission.CAMERA,
                MainActivity.CAMERA_PERMISSION_CODE
            )
        }

        binding.btnLocation.setOnClickListener {
            checkPermissionForLocation(
                Manifest.permission.ACCESS_FINE_LOCATION,
                MainActivity.LOCATION_PERMISSION_CODE
            )
        }
    }


    // chake permission for camera
    private fun checkPermissionForCamera(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity2,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity2, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@MainActivity2, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, pic_id)

        }
    }


    //check permission for storage
    private fun checkPermissionForStorage(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity2,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity2, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@MainActivity2, "Permission already granted", Toast.LENGTH_SHORT)
                .show()

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pic_img)
        }
    }

    //check permission for location
    private fun checkPermissionForLocation(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity2,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !==
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity2,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity2,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity2,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        }
    }


    //for camera open
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            binding.img.setImageBitmap(photo)
        }

        //for gallery
        if (resultCode == RESULT_OK && requestCode == pic_img) {
            imageUri = data?.data
            binding.img.setImageURI(imageUri)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (requestCode == MainActivity.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity2, "Camera Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity2, "Camera Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        } else if (requestCode == MainActivity.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity2, "Storage Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity2, "Storage Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        } else if (requestCode == MainActivity.LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity2, "Location Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity2, "Location Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}