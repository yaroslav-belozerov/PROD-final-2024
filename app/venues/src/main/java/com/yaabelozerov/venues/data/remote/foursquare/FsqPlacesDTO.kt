package com.yaabelozerov.venues.data.remote.foursquare

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yaabelozerov.venues.data.remote.foursquare.model.Result

@JsonClass(generateAdapter = true)
data class FsqPlacesDTO(
    @Json(name = "results")
    var results: List<Result>? = null,
)
