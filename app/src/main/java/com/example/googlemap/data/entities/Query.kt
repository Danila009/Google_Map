package com.example.googlemap.data.entities

data class Query(
    val coordinates: List<List<Double>> = listOf(),
    val format: String = "",
    val profile: String = ""
)