package com.yaabelozerov.venues.data.remote.model

import com.squareup.moshi.Json


class Center {
    @Json(name = "latitude")
    var latitude: Double? = null

    @Json(name = "longitude")
    var longitude: Double? = null
}