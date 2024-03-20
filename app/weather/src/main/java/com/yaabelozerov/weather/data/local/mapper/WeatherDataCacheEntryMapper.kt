package com.yaabelozerov.weather.data.local.mapper

import com.yaabelozerov.weather.data.local.model.CacheMapper
import com.yaabelozerov.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.weather.domain.model.WeatherData
import java.time.LocalDateTime

class WeatherDataCacheEntryMapper:
    com.yaabelozerov.weather.data.local.model.CacheMapper<WeatherData, WeatherDataCacheEntry> {
    override fun mapToCacheEntry(model: WeatherData): WeatherDataCacheEntry {
        return WeatherDataCacheEntry(id = "${model.lat}${model.lon}", data = model, expiresAt = LocalDateTime.now())
    }

    override fun mapToDomainModel(model: WeatherDataCacheEntry): WeatherData {
        return model.data
    }
}