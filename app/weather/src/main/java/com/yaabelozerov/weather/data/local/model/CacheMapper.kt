package com.yaabelozerov.weather.data.local.model

interface CacheMapper<data, entry> {
    fun mapToCacheEntry(model: data): entry

    fun mapToDomainModel(model: entry): data
}
