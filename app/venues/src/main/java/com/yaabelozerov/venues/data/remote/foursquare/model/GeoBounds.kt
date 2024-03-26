package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeoBounds(
    @Json(name = "circle")
    var circle: Circle? = null,
)
