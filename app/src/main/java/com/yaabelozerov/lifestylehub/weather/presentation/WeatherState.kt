package com.yaabelozerov.lifestylehub.weather.presentation

import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData

data class WeatherState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
