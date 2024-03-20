package com.yaabelozerov.weather.data.local.model

import com.yaabelozerov.weather.domain.model.WeatherData
import java.time.LocalDateTime

data class WeatherDataCacheEntry(override val id: String, override val data: WeatherData,
                                 override val expiresAt: LocalDateTime
) : com.yaabelozerov.weather.data.local.model.CacheEntry<WeatherData>