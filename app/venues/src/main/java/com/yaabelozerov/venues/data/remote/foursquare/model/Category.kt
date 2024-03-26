package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Category {
    @Json(name = "short_name")
    var shortName: String? = null
}
