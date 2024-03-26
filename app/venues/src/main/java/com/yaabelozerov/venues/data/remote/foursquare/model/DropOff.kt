package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DropOff(
    @Json(name = "latitude")
    var latitude: Double? = null,
    @Json(name = "longitude")
    var longitude: Double? = null,
)
