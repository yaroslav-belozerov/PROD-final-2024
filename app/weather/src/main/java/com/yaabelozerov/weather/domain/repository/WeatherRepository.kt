package com.yaabelozerov.weather.domain.repository

import com.yaabelozerov.weather.domain.model.WeatherData
import com.yaabelozerov.weather.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double, lang: String?): Resource<WeatherData>
}