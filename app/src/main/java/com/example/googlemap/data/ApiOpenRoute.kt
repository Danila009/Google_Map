package com.example.googlemap.data

import com.example.googlemap.data.entities.Directions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiOpenRoute {

    @GET("/v2/directions/{profile}")
    suspend fun getDirections(
        @Path("profile") profile:String = "foot-walking",
        @Query("api_key") api_key:String = "5b3ce3597851110001cf6248590a5b15165142929ec3ca6cccdfbe77",
        @Query("start") start:String,
        @Query("end") end:String
    ):Response<Directions>
}