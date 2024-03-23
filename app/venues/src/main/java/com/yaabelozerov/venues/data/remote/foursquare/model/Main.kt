package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Main {
    @Json(name = "latitude")
    var latitude: Double? = null

    @Json(name = "longitude")
    var longitude: Double? = null
}
