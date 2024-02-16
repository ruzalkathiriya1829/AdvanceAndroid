package com.example.googlemapproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemapproject.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Polyline

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var polyline : Polyline ?= null
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(21.20825693104006, 72.89541605294379)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        getDirection()
    }

    private fun getDirection() {
        GoogleDirection.withServerKey("AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg")
            .from(LatLng(37.7681994, -122.444538))
            .to(LatLng(37.7749003,-122.4034934))
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

    private fun   directionsuccess( direction : Direction ) {
        try {
            if (direction.isOK()) {
                var route = direction.getRouteList().get(0);
                if (route != null && !route.getLegList().isEmpty()) {
                    var distance = route.getLegList().get(0).getDistance();
                    var duration = route.getLegList().get(0).getDuration();

                    Log.e("TAG", "directionsuccess: "+distance.text.toString() +"   "+duration.text.toString() )
                    var directionPositionList = route.getLegList().get(0).getDirectionPoint();
                    if (!directionPositionList.isEmpty()) {
                        if (polyline != null) {
                            polyline?.remove();
                        }
                        polyline = mMap.addPolyline(
                            DirectionConverter.createPolyline(
                                this,
                                directionPositionList,
                                4,
                                ContextCompat.getColor(this, R.color.black)
                            )
                        );
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        setCameraWithCoordinationBounds(route);
                    } else {
                        Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show();
            }
        } catch (e: Exception) {
            e.printStackTrace();
            Toast.makeText(this, "catch", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "directionsuccess:==> "+e.message)
        }
    }

    private fun setCameraWithCoordinationBounds(  route : Route) {
        var southwest = route.getBound().getSouthwestCoordination().getCoordination();
        var northeast = route.getBound().getNortheastCoordination().getCoordination();
        var bounds =  LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,100));
       }
}