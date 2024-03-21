package com.yaabelozerov.venues.data.remote.model

import com.squareup.moshi.Json

class Context {
    @Json(name = "geo_bounds")
    var geoBounds: GeoBounds? = null
}