package com.example.googlemaplocationproject.DrawPath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.config.GoogleDirectionConfiguration
import com.akexorcist.googledirection.constant.TransportMode
import com.example.googlemaplocationproject.R
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.akexorcist.googledirection.util.execute
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemaplocationproject.databinding.ActivityAlternativeDirectionBinding
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.BuildConfig

class AlternativeDirectionActivity : AppCompatActivity(){

    private var googleMap: GoogleMap? = null
    private lateinit var binding: ActivityAlternativeDirectionBinding

    companion object {
        private const val serverKey = "AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg"
        private val origin = LatLng(21.196356241550635, 72.79291911135854)
        private val destination = LatLng(21.19603614534015, 72.80785365056785)
        private val pathColors = arrayOf(
            R.color.path_color_1,
            R.color.path_color_2,
            R.color.path_color_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlternativeDirectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRequestDirection.setOnClickListener { requestDirection() }

        (supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment).getMapAsync { googleMap ->
            this.googleMap = googleMap
        }

    }
    private fun requestDirection() {
        showSnackbar(getString(R.string.direction_requesting))
        GoogleDirectionConfiguration.getInstance().isLogEnabled = BuildConfig.DEBUG
        GoogleDirection.withServerKey(serverKey)
            .from(origin)
            .to(destination)
            .transportMode(TransportMode.DRIVING)
            .alternativeRoute(true)
            .execute(
                onDirectionSuccess = { direction -> onDirectionSuccess(direction) },
                onDirectionFailure = { t -> onDirectionFailure(t) }
            )
    }

    private fun onDirectionSuccess(direction: Direction?) {
        direction?.let {
            showSnackbar(getString(R.string.success_with_status, direction.status))
            if (direction.isOK) {
                googleMap?.addMarker(MarkerOptions().position(origin))
                googleMap?.addMarker(MarkerOptions().position(destination))
                for (i in 0 until direction.routeList.size) {
                    val route = direction.routeList[i]
                    val color = ContextCompat.getColor(this, pathColors[i % pathColors.size])
                    val directionPositionList = route.legList[0].directionPoint
                    googleMap?.addPolyline(
                        DirectionConverter.createPolyline(
                            this,
                            directionPositionList,
                            5,
                            color
                        )
                    )
                }
                setCameraWithCoordinationBounds(direction.routeList[0])
                binding.buttonRequestDirection.visibility = View.GONE
            } else {
                showSnackbar(direction.status)
            }
        } ?: run {
            showSnackbar(getString(R.string.success_with_empty))
        }
    }

    private fun onDirectionFailure(t: Throwable) {
        showSnackbar(t.message)
    }

    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

    private fun showSnackbar(message: String?) {
        message?.let {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
        }
    }
}