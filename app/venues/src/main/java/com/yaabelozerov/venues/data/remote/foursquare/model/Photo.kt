package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Photo {
    @Json(name = "id")
    var id: String? = null

    @Json(name = "created_at")
    var createdAt: String? = null

    @Json(name = "prefix")
    var prefix: String? = null

    @Json(name = "suffix")
    var suffix: String? = null

    @Json(name = "width")
    var width: Int? = null

    @Json(name = "height")
    var height: Int? = null

    @Json(name = "classifications")
    var classifications: List<String>? = null
}
