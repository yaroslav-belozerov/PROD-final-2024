package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json


class RelatedPlaces {
    @Json(name = "parent")
    var parent: Parent? = null

    @Json(name = "children")
    var children: List<Child>? = null
}