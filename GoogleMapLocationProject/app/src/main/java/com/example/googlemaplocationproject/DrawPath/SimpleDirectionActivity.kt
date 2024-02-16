package com.example.googlemaplocationproject.DrawPath

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.googledirection.BuildConfig
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.config.GoogleDirectionConfiguration
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.akexorcist.googledirection.util.execute
import com.example.googlemaplocationproject.R
import com.example.googlemaplocationproject.databinding.ActivitySimpleDirectionBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class SimpleDirectionActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private lateinit var binding: ActivitySimpleDirectionBinding

    companion object {
        private const val serverKey = "AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg"
        private val origin = LatLng(21.14370840198671, 72.84291682646887)
        private val destination = LatLng(21.131539930261457, 72.85252986320131)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleDirectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRequestDirection.setOnClickListener { requestDirection() }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun requestDirection() {
        showSnackbar(getString(R.string.direction_requesting))
        GoogleDirectionConfiguration.getInstance().isLogEnabled = BuildConfig.DEBUG
        GoogleDirection.withServerKey(serverKey)
            .from(origin)
            .to(destination)
            .transportMode(TransportMode.DRIVING)
            .execute(
                onDirectionSuccess = { direction -> onDirectionSuccess(direction) },
                onDirectionFailure = { t -> onDirectionFailure(t) }
            )
    }

    private fun onDirectionFailure(t: Throwable) {
        t.message?.let { showSnackbar(it) }
    }

    private fun onDirectionSuccess(direction: Direction?) {

        direction?.let {
            showSnackbar(getString(R.string.success_with_status, direction.status))
            if (direction.isOK) {
                val route = direction.routeList[0]
                mMap?.addMarker(MarkerOptions().position(origin))
                mMap?.addMarker(MarkerOptions().position(destination))
                val directionPositionList = route.legList[0].directionPoint
                mMap?.addPolyline(
                    DirectionConverter.createPolyline(
                        this,
                        directionPositionList,
                        5,
                        Color.RED
                    )
                )
                setCameraWithCoordinationBounds(route)
                binding.buttonRequestDirection.visibility = View.GONE
            } else {
                showSnackbar(direction.status)
            }
        } ?: run {
            showSnackbar(getString(R.string.success_with_empty))
        }

    }

    private fun setCameraWithCoordinationBounds(route: Route) {

        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        mMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
        
    }

    private fun showSnackbar(message: String?) {
        message?.let {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
        }
    }
}