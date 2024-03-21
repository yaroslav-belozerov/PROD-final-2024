package com.yaabelozerov.venues.data.remote

import com.squareup.moshi.Json
import com.yaabelozerov.venues.data.remote.model.Context
import com.yaabelozerov.venues.data.remote.model.Result

class FsqPlacesDTO {
    @Json(name = "results")
    var results: List<Result>? = null
//
//    @Json(name = "context")
//    var context: Context? = null
}