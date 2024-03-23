package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Parent {
    @Json(name = "fsq_id")
    var fsqId: String? = null

    @Json(name = "categories")
    var categories: List<Category>? = null

    @Json(name = "name")
    var name: String? = null
}
