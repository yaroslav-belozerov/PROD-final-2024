package com.yaabelozerov.venues.data.remote.foursquare.model

import android.graphics.drawable.Icon
import com.squareup.moshi.Json

class Category {
    @Json(name = "id")
    var id: Int? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "short_name")
    var shortName: String? = null

    @Json(name = "plural_name")
    var pluralName: String? = null

    @Json(name = "icon")
    var icon: Icon? = null
}
