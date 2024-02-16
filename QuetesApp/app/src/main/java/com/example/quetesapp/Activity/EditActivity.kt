package com.example.quetesapp.Activity

import CustomDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.quetesapp.R
import com.example.quetesapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding

    var id: Int = 0
    lateinit var Quotes: String
    lateinit var title: String
    var currentIndex = 0
    var img =
        arrayOf(
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5,
            R.drawable.bg6,
            R.drawable.bg7,
            R.drawable.bg8,
            R.drawable.bg9,
            R.drawable.bg10,
            R.drawable.bg11,
            R.drawable.bg12
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.black)

        initview()

    }

    private fun initview() {

        if (intent != null) {

            id = intent.getIntExtra("id", id)
            Quotes = intent.getStringExtra("Quetos").toString()
            title = intent.getStringExtra("name").toString()
            binding.txtTitle.text = title
            binding.txtQuotes.text = Quotes
        }


        binding.imgDwonlod.setOnClickListener {
            val pic: View = binding.layoutdownload
            pic.isDrawingCacheEnabled = true
            val hight: Int = pic.height
            val width: Int = pic.width
            pic.layout(0, 0, width, hight)
            pic.buildDrawingCache(true)
            val bm: Bitmap = Bitmap.createBitmap(pic.drawingCache)
            pic.isDrawingCacheEnabled = false
            Toast.makeText(this, "Download Successfully", Toast.LENGTH_SHORT).show()
            MediaStore.Images.Media.insertImage(contentResolver, bm, null, null)
        }


        binding.layoutdownload.setOnClickListener {

            changeBackgroundImage()
        }

        binding.imgCopy.setOnClickListener {

            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Quetos", Quotes)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copy on clipboard ", Toast.LENGTH_SHORT).show()
        }

        binding.imgShare.setOnClickListener {

            val dataToShare = Quotes
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, dataToShare)
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.imgChangeBg.setOnClickListener {

            changeBackgroundImage()

        }

        binding.imGallery.setOnClickListener {


            CustomDialog(applicationContext).show(getString(R.string.alert_title),
                getString(R.string.are_you_sure)) {

                Toast.makeText(applicationContext, it.toString(),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun changeBackgroundImage() {
        if (currentIndex >= img.size) {
            currentIndex = 0 // Reset the index if it exceeds the array size
        }
        binding.backgroundImage.setBackgroundResource(img[currentIndex])
        currentIndex++
    }
}