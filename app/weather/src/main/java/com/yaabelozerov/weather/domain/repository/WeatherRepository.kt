package com.yaabelozerov.weather.domain.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.weather.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(
        lat: Double,
        lon: Double,
        lang: String?,
    ): Resource<WeatherData>
}
