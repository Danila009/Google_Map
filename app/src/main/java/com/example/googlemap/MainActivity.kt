package com.example.googlemap

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.googlemap.data.RetrofitInst
import com.example.googlemap.data.entities.Directions
import com.example.googlemap.data.entities.MarkerInfo
import com.example.googlemap.data.entities.Search
import com.example.googlemap.style.MapStyle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = RetrofitInst.api
        val apiOpenRoute = RetrofitInst.apiOpenRoute
        val mainViewModelFactory = MainViewModelFactory(api = api, apiOpenRoute = apiOpenRoute)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        setContent {
            var search by remember { mutableStateOf("") }
            var end by remember { mutableStateOf("") }
            var style by remember { mutableStateOf(false) }
            var searchResult by remember { mutableStateOf(listOf<Search>()) }
            var markerInfo by remember { mutableStateOf(MarkerInfo()) }
            var direction by remember { mutableStateOf(Directions()) }
            val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
            var latLngClick:LatLng? by remember { mutableStateOf(null) }
            val permission = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(0.1, 0.1), 1f)
            }

            LaunchedEffect(key1 = Unit, block = {
                permission.launchPermissionRequest()
            })

            LaunchedEffect(key1 = latLngClick, block = {
                latLngClick?.let {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
                }
            })

            lifecycleScope.launch {
                mainViewModel.responseSearch.collect {
                    searchResult = it
                }
            }

            lifecycleScope.launch {
                mainViewModel.responseMarkerInfo.collect {
                    markerInfo = it
                }
            }

            lifecycleScope.launch {
                mainViewModel.responseDirection.collect {
                    direction = it
                }
            }

            BackdropScaffold(
                scaffoldState = backdropState,
                peekHeight = 300.dp,
                backLayerBackgroundColor = Color.Gray,
                appBar = {

                }, backLayerContent = {
                    PermissionRequired(
                        permissionState = permission,
                        permissionNotAvailableContent = {

                        },
                        permissionNotGrantedContent = {

                        },
                        content = {
                            GoogleMap(
                                properties = MapProperties(
                                    mapType = if(style) MapType.NORMAL else MapType.SATELLITE,
                                    isMyLocationEnabled = true,
                                    mapStyleOptions = if(style) MapStyleOptions(MapStyle.jsonDark) else null
                                ),
                                cameraPositionState = cameraPositionState,
                                googleMapOptionsFactory = {
                                    GoogleMapOptions()
                                },
                                onMapClick = {
                                    latLngClick = it
                                },
                                content = {
                                    latLngClick?.let {
                                        mainViewModel.getMarkerInfo(it.latitude.toString(), it.longitude.toString())
                                        Toast.makeText(this, markerInfo.display_name, Toast.LENGTH_SHORT).show()
                                        Marker(
                                            position = it,
                                            title = markerInfo.display_name
                                        )
                                    }
                                    searchResult.forEach { item ->
                                        val position = try {
                                            LatLng(item.lat.toDouble(), item.lon.toDouble())
                                        }catch (e:Exception){
                                            val lat = item.lat.toInt() + 0.1
                                            val lon = item.lon.toInt() + 0.1
                                            LatLng(lat, lon)
                                        }
                                        Marker(
                                            position = position,
                                            title = item.display_name
                                        )
                                        val positionPolygon = try {
                                            val bounding = item.boundingbox.map { it.toDouble() }
                                            listOf(
                                                LatLng(bounding[0], bounding[2]),
                                                LatLng(bounding[1], bounding[3])
                                            )
                                        }catch (e:Exception){
                                            emptyList()
                                        }
                                    }
                                    direction.features.forEach {
                                        val coordinates = remember{ mutableListOf<LatLng>() }
                                        it.geometry.coordinates.forEach {  coordinate ->
                                            coordinates.add(
                                                LatLng(coordinate[1], coordinate[0])
                                            )
                                            Log.e("Response", coordinate.toString())
                                        }
                                        Log.e("Response", coordinates.toString())
                                        Polygon(
                                            points = coordinates,
                                            strokeColor = Color.Red
                                        )
                                    }
                                }
                            )
                        }
                    )
                }, frontLayerContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier = Modifier.padding(5.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    mainViewModel.getSearch(
                                        street = search,
                                        cite = ""
                                    )
                                }
                            )
                        )

                        OutlinedTextField(
                            value = end,
                            onValueChange = { end = it },
                            modifier = Modifier.padding(5.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                            keyboardActions = KeyboardActions(
                                onGo = {
                                    latLngClick?.let {
                                        mainViewModel.getDirections(
                                            start = getGPS(),
                                            end = "${it.longitude},${it.latitude}"
                                        )
                                    }
                                }
                            )
                        )

                        OutlinedButton(onClick = { style = !style }) {
                            Text(text = "Style")
                        }

                    }
                }
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getGPS(): String {
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        val providers = lm.getProviders(true)
        var l: Location? = null
        for (i in providers.indices.reversed()) {
            l = lm.getLastKnownLocation(providers[i])
            if (l != null) break
        }
        val gps = DoubleArray(2)
        if (l != null) {
            gps[0] = l.latitude
            gps[1] = l.longitude
        }
        return "${gps[1]},${gps[0]}"
    }
}
