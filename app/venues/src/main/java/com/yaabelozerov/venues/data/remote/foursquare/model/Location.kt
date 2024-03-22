package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json


class Location {
    @Json(name = "address")
    var address: String? = null

    @Json(name = "address_extended")
    var addressExtended: String? = null

    @Json(name = "country")
    var country: String? = null

    @Json(name = "cross_street")
    var crossStreet: String? = null

    @Json(name = "formatted_address")
    var formattedAddress: String? = null

    @Json(name = "locality")
    var locality: String? = null

    @Json(name = "postcode")
    var postcode: String? = null

    @Json(name = "region")
    var region: String? = null
}