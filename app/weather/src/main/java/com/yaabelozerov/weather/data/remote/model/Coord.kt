package com.yaabelozerov.weather.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lon")
    var lon: Double? = null,
    @Json(name = "lat")
    var lat: Double? = null,
)
