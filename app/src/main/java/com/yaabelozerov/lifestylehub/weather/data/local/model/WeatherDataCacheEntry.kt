package com.yaabelozerov.lifestylehub.weather.data.local.model

import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import java.time.LocalDateTime

data class WeatherDataCacheEntry(override val id: String, override val data: WeatherData,
                                 override val expiresAt: LocalDateTime
) : CacheEntry<WeatherData>