package com.yaabelozerov.weather.domain.model

data class WeatherData(
    val place: String,
    val description: String,
    val iconUrl: String,
    val temperature: String,
    val feelsLike: String,
    val lat: String,
    val lon: String
)