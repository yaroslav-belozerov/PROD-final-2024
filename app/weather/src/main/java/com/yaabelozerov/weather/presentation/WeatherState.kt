package com.yaabelozerov.weather.presentation

import com.yaabelozerov.weather.domain.model.WeatherData

data class WeatherState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
)
