package com.yaabelozerov.lifestylehub.weather.domain.model

data class WeatherData(
    val place: String,
    val description: String,
    val iconUrl: String,
    val temperature: Int,
    val feelsLike: Int,
    val lat: String,
    val lon: String
)