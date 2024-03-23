package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Result {
    @Json(name = "fsq_id")
    var fsqId: String? = null

//    @Json(name = "categories")
//    var categories: List<Category>? = null
//
//    @Json(name = "chains")
//    var chains: List<Any>? = null

    @Json(name = "closed_bucket")
    var closedBucket: String? = null

    @Json(name = "distance")
    var distance: Int? = null

//    @Json(name = "geocodes")
//    var geocodes: Geocodes? = null
//
//    @Json(name = "link")
//    var link: String? = null

    @Json(name = "location")
    var location: Location? = null

    @Json(name = "name")
    var name: String? = null

//    @Json(name = "related_places")
//    var relatedPlaces: RelatedPlaces? = null
//
//    @Json(name = "timezone")
//    var timezone: String? = null
}
