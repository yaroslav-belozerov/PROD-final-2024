package com.yaabelozerov.venues.data.remote.model

import com.squareup.moshi.Json


class GeoBounds {
    @Json(name = "circle")
    var circle: Circle? = null
}