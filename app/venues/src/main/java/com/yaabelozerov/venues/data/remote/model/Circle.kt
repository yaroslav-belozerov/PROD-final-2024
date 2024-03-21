package com.yaabelozerov.venues.data.remote.model

import com.squareup.moshi.Json


class Circle {
    @Json(name = "center")
    var center: Center? = null

    @Json(name = "radius")
    var radius: Int? = null
}