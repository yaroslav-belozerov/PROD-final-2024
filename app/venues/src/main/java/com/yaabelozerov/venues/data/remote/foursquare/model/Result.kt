package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "fsq_id")
    var fsqId: String? = null,
    @Json(name = "categories")
    var categories: List<Category>? = null,
    @Json(name = "closed_bucket")
    var closedBucket: String? = null,
    @Json(name = "distance")
    var distance: Int? = null,
    @Json(name = "geocodes")
    var geocodes: Geocodes? = null,
    @Json(name = "location")
    var location: Location? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "photos")
    var photos: List<Photo>? = null,
)
