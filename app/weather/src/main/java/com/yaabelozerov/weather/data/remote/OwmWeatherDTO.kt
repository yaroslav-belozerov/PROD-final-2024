package com.yaabelozerov.weather.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yaabelozerov.weather.data.remote.model.Coord
import com.yaabelozerov.weather.data.remote.model.Main
import com.yaabelozerov.weather.data.remote.model.Weather

@JsonClass(generateAdapter = true)
data class OwmWeatherDTO(
    @Json(name = "coord") var coord: Coord? = null,

    @Json(name = "weather") var weather: List<Weather>? = null,

    @Json(name = "main") var main: Main? = null,

    @Json(name = "name") var name: String? = null,
)