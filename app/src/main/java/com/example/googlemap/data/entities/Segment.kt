package com.example.googlemap.data.entities

data class Segment(
    val distance: Double,
    val duration: Double,
    val steps: List<Step>
)