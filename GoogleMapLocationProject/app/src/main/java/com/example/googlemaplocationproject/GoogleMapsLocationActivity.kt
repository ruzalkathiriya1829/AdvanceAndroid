package com.example.googlemaplocationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemaplocationproject.databinding.ActivityGoogleMapsLocationBinding

class GoogleMapsLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val Surat = LatLng(21.175813349535087, 72.82961840882652)
        mMap.addMarker(MarkerOptions().position(Surat).title("Marker in Surat"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Surat))

        val Ahmedabad = LatLng(23.02205400366074, 72.56942404464372)
        mMap.addMarker(MarkerOptions().position(Ahmedabad).title("Marker in Ahmedabad"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Ahmedabad))


        val Rajkot = LatLng(22.305233150239992, 70.80137890421697)
        mMap.addMarker(MarkerOptions().position(Rajkot).title("Marker in Rajkot"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Rajkot))


        val Junagadh = LatLng(21.523521929219203, 70.45390982047168)
        mMap.addMarker(MarkerOptions().position(Junagadh).title("Marker in Junagadh"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Junagadh))

        val Bhavnagar = LatLng(21.765901066205327, 72.15335996352422)
        mMap.addMarker(MarkerOptions().position(Bhavnagar).title("Marker in Bhavnagar"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Bhavnagar))

        val Vadodara = LatLng(22.31164113045161, 73.19287240254314)
        mMap.addMarker(MarkerOptions().position(Vadodara).title("Marker in Vadodara"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Vadodara))

        val Dwarka = LatLng(22.244883288538865, 68.96861915720486)
        mMap.addMarker(MarkerOptions().position(Dwarka).title("Marker in Dwarka"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Dwarka))

        val Jamnagar = LatLng(22.473938312342533, 70.0549487840589)
        mMap.addMarker(MarkerOptions().position(Jamnagar).title("Marker in Jamnagar"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jamnagar))

        val Surendranagar = LatLng(22.724614727540036, 71.62936983069783)
        mMap.addMarker(MarkerOptions().position(Surendranagar).title("Marker in Surendranagar"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Surendranagar))

        val Rajasthan = LatLng(26.86536031556912, 72.97839187602966)
        mMap.addMarker(MarkerOptions().position(Rajasthan).title("Marker in Rajasthan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Rajasthan))

    }
}