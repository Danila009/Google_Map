package com.example.googlemap.data

import com.example.googlemap.data.entities.MarkerInfo
import com.example.googlemap.data.entities.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/search")
    suspend fun getSearch(
        @Query("format") format:String = "json",
        @Query("city") city:String,
        @Query("street") street:String
    ):Response<List<Search>>

    @GET("/reverse")
    suspend fun getMarkerInfo(
        @Query("format") format:String = "json",
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("zoom") zoom:Int = 18,
        @Query("addressdetails") addressdetails:Int = 1
    ):Response<MarkerInfo>

    
}