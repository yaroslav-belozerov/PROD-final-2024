package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class GeoBounds {
    @Json(name = "circle")
    var circle: Circle? = null
}
