package com.example.google_maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.google_maps.databinding.ActivityXmlGoogleMapsBinding
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions

class XML_Google_Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityXmlGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityXmlGoogleMapsBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.Googlemap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.addMarker(MarkerOptions()
            .position(sydney).title("Marker in Sydney")
            .snippet("В Сиднее есть что то"))
    }
}