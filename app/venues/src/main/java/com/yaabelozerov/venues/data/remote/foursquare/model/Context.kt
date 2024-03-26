package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Context(
    @Json(name = "geo_bounds")
    var geoBounds: GeoBounds? = null,
)
