package com.example.google_maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.google_maps.databinding.ActivityXmlGoogleMapsBinding
import com.google.android.libraries.maps.GoogleMap

class XMLMaps : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityXmlGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xmlmaps)

    }
}