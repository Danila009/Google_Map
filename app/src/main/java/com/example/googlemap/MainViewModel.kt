package com.example.googlemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemap.data.Api
import com.example.googlemap.data.entities.MarkerInfo
import com.example.googlemap.data.entities.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val api: Api
):ViewModel() {

    private val _responseSearch = MutableStateFlow(listOf<Search>())
    val responseSearch = _responseSearch.asStateFlow()

    private val _responseMarkerInfo = MutableStateFlow(MarkerInfo())
    val responseMarkerInfo = _responseMarkerInfo.asStateFlow()

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
}