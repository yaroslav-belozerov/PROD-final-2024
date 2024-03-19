package com.yaabelozerov.lifestylehub.weather.data.local.mapper

import com.yaabelozerov.lifestylehub.weather.data.local.model.CacheMapper
import com.yaabelozerov.lifestylehub.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import java.time.LocalDateTime

class WeatherDataCacheEntryMapper: CacheMapper<WeatherData, WeatherDataCacheEntry> {
    override fun mapToCacheEntry(model: WeatherData): WeatherDataCacheEntry {
        return WeatherDataCacheEntry(id = "${model.lat}${model.lon}", data = model, expiresAt = LocalDateTime.now())
    }

    override fun mapToDomainModel(model: WeatherDataCacheEntry): WeatherData {
        return model.data
    }
}