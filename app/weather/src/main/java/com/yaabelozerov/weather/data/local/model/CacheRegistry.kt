package com.yaabelozerov.weather.data.local.model

interface CacheRegistry<T> {
    fun getEntry(id: String): T?

    fun putEntry(
        id: String,
        entry: T,
    )
}
