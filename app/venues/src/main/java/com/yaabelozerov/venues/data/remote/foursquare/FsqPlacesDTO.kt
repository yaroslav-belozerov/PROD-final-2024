package com.yaabelozerov.venues.data.remote.foursquare

import com.squareup.moshi.Json
import com.yaabelozerov.venues.data.remote.foursquare.model.Result

class FsqPlacesDTO {
    @Json(name = "results")
    var results: List<Result>? = null
}
