package com.example.quetesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.quetesapp.R

class SplaceScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace_screen)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.black)

        initview()
    }

    private fun initview() {


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splace_screen)

        Handler().postDelayed({

            val i = Intent(this@SplaceScreenActivity, MainActivity::class.java)
            startActivity(i)

            finish()
        }, 2000)

    }
}