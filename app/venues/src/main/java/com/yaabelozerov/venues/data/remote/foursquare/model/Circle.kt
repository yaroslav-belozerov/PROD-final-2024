package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Circle(
    @Json(name = "center")
    var center: Center? = null,
    @Json(name = "radius")
    var radius: Int? = null,
)
