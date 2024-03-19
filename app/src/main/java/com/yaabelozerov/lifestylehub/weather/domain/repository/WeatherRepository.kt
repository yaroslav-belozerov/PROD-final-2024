package com.yaabelozerov.lifestylehub.weather.domain.repository

import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import com.yaabelozerov.lifestylehub.weather.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double, lang: String?): Resource<WeatherData>
}