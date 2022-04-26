package com.example.googlemap.data.entities

data class Metadata(
    val attribution: String = "",
    val engine: Engine = Engine(),
    val query: Query = Query(),
    val service: String = ""
)