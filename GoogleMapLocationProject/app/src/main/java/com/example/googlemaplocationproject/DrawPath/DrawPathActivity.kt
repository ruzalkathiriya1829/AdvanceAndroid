package com.example.googlemaplocationproject.DrawPath

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.example.googlemaplocationproject.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemaplocationproject.databinding.ActivityDrawPathBinding
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Polyline

class DrawPathActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDrawPathBinding
    private var polyline : Polyline?= null

    private val origin = LatLng(21.207520392794326, 72.8819014678575)
    private val destination = LatLng(21.20831689387521, 72.8957915358353)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawPathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        getDirection()
    }

    private fun getDirection() {
        GoogleDirection.withServerKey("AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg")
            .from(origin)
            .to(destination)
            .avoid(AvoidType.FERRIES)
            .avoid(AvoidType.HIGHWAYS)
            .execute(

                object : DirectionCallback {
                    override fun onDirectionSuccess(direction: Direction?) {
                        if(direction?.isOK() == true) {
//                        // Do something


                            directionsuccess(direction)
                        } else {
                            // Do something
                        }
                    }

                    override fun onDirectionFailure(t: Throwable) {

                    }
                }

            )
    }

    private fun directionsuccess(direction: Direction) {

        try {
            if (direction.isOK()) {

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

//                var route = direction.getRouteList().get(0);
//                if (route != null && !route.getLegList().isEmpty()) {
//                    var distance = route.getLegList().get(0).getDistance();
//                    var duration = route.getLegList().get(0).getDuration();
//
//                    Log.e("TAG", "directionsuccess: "+distance.text.toString() +"   "+duration.text.toString() )
//                    var directionPositionList = route.getLegList().get(0).getDirectionPoint();
//                    if (!directionPositionList.isEmpty()) {
//                        if (polyline != null) {
//                            polyline?.remove();
//                        }
//                        polyline = mMap.addPolyline(
//                            DirectionConverter.createPolyline(
//                                this,
//                                directionPositionList,
//                                4,
//                                ContextCompat.getColor(this, R.color.black)
//                            )
//                        )
//                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
//                        setCameraWithCoordinationBounds(route);
//                    } else {
//                        Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
//                }
            } else {
                Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
            }
        } catch (e: Exception) {
            e.printStackTrace();
            Toast.makeText(this, "catch", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "directionsuccess:==> "+e.message)
        }
    }

    private fun setCameraWithCoordinationBounds(route: Route) {

        var southwest = route.getBound().getSouthwestCoordination().getCoordination();
        var northeast = route.getBound().getNortheastCoordination().getCoordination();
        var bounds =  LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,100))

    }
}