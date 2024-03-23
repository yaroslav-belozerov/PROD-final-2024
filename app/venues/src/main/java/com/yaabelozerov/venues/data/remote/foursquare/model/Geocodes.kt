package com.yaabelozerov.venues.data.remote.foursquare.model

import com.squareup.moshi.Json

class Geocodes {
    @Json(name = "main")
    var main: Main? = null

    @Json(name = "drop_off")
    var dropOff: DropOff? = null

    @Json(name = "roof")
    var roof: Roof? = null
}
