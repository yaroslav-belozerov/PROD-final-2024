package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json


class Icon {
    @Json(name = "prefix")
    var prefix: String? = null

    @Json(name = "suffix")
    var suffix: String? = null
}