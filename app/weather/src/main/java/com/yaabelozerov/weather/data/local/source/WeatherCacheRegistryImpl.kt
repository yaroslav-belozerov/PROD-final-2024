package com.yaabelozerov.weather.data.local.source

import com.yaabelozerov.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.weather.data.local.model.CacheRegistry

class WeatherCacheRegistryImpl : CacheRegistry<WeatherDataCacheEntry> {
    private val cache = mutableMapOf<String, WeatherDataCacheEntry>()

    override fun getEntry(id: String): WeatherDataCacheEntry? {
//        Log.d("CacheRegistry", "getEntry: $id")
        return cache[id]
    }

    override fun putEntry(id: String, entry: WeatherDataCacheEntry) {
//        Log.d("CacheRegistry", "putEntry: $id ${entry.data}")
        cache[id] = entry
    }
}