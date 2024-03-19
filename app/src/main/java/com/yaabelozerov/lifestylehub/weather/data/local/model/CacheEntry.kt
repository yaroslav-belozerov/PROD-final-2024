package com.yaabelozerov.lifestylehub.weather.data.local.model

import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import java.time.LocalDateTime

interface CacheEntry<T> {
    val id: String
    val expiresAt: LocalDateTime
    val data: T
}