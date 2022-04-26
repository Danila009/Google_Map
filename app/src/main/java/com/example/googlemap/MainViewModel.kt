package com.example.googlemap

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemap.data.Api
import com.example.googlemap.data.ApiOpenRoute
import com.example.googlemap.data.entities.Directions
import com.example.googlemap.data.entities.MarkerInfo
import com.example.googlemap.data.entities.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val api: Api,
    private val apiOpenRoute: ApiOpenRoute
):ViewModel() {

    private val _responseSearch = MutableStateFlow(listOf<Search>())
    val responseSearch = _responseSearch.asStateFlow()

    private val _responseMarkerInfo = MutableStateFlow(MarkerInfo())
    val responseMarkerInfo = _responseMarkerInfo.asStateFlow()

    private val _responseDirection = MutableStateFlow(Directions())
    val responseDirection = _responseDirection.asStateFlow()

    fun getSearch(cite:String, street: String){
        viewModelScope.launch {
            val response = api.getSearch(
                city = cite,
                street = street
            ).body()!!
            _responseSearch.value = response
        }
    }

    fun getMarkerInfo(lat:String, lon:String, ){
        viewModelScope.launch {
            val response = api.getMarkerInfo(
                lat = lat, lon = lon
            ).body()!!
            _responseMarkerInfo.value = response
        }
    }

    fun getDirections(end:String, start:String){
        viewModelScope.launch {
            val response = apiOpenRoute.getDirections(
                end = end,
                start = start
            )
            _responseDirection.value = response.body()!!
            Log.e("Response", response.toString())
            Log.e("Response", "$start / $end")
        }
    }
}