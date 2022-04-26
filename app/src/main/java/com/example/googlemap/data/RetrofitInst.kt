package com.example.googlemap.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitInst {

    companion object{
        val api: Api = Retrofit.Builder()
            .baseUrl(" https://nominatim.openstreetmap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val apiOpenRoute: ApiOpenRoute = Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiOpenRoute::class.java)
    }
}