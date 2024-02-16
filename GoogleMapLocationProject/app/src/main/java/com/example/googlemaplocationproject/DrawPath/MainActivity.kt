package com.example.googlemaplocationproject.DrawPath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemaplocationproject.GoogleMapsLocationActivity
import com.example.googlemaplocationproject.R
import com.example.googlemaplocationproject.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSimple.setOnClickListener { goToSimpleDirection() }
        binding.buttonTransit.setOnClickListener { goToTransitDirection() }
        binding.buttonAlternative.setOnClickListener { goToAlternativeDirection() }
        binding.buttonWaypoints.setOnClickListener { goToWaypointsDirection() }
        binding.buttonMultiLocation.setOnClickListener { goToMultiDirection() }
    }

    private fun goToMultiDirection() {
        openActivity(GoogleMapsLocationActivity::class.java)
    }

    private fun goToSimpleDirection() {
        openActivity(SimpleDirectionActivity::class.java)
    }

    private fun goToTransitDirection() {
        openActivity(TransitDirectionActivity::class.java)
    }

    private fun goToAlternativeDirection() {
        openActivity(AlternativeDirectionActivity::class.java)
    }

    private fun goToWaypointsDirection() {
        openActivity(WaypointsDirectionActivity::class.java)
    }

    private fun openActivity(cs: Class<*>) {
        startActivity(Intent(this, cs))
    }
}