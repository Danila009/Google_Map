package com.example.google_maps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.google_maps.ui.theme.Google_MapsTheme
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import java.lang.IllegalStateException

class ComposeMaps : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                        MyMap(
                            modifier = Modifier
                                .padding(12.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding()
                        ){
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyMap(
    modifier: Modifier,
    onReady:(GoogleMap)->Unit
) {
    val context = LocalContext.current
    val mapView = remember{
        MapView(context)
    }
    val lifecycle= LocalLifecycleOwner.current.lifecycle

    lifecycle.addObserver(RememberMap(mapView))
    AndroidView(factory = {
                          mapView.apply {
                              mapView.getMapAsync{ googleMap->
                                  onReady(googleMap)
                              }
                          }
    },modifier = modifier)
}

@SuppressLint("ComposableNaming")
@Composable
fun RememberMap(map:MapView):LifecycleEventObserver{

    return remember {
        LifecycleEventObserver { source, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME ->map.onResume()
                Lifecycle.Event.ON_PAUSE ->map.onPause()
                Lifecycle.Event.ON_STOP ->map. onStop()
                Lifecycle.Event.ON_DESTROY ->map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}