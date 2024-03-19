package com.yaabelozerov.lifestylehub.weather.data.local.model

interface CacheRegistry<T> {
    fun getEntry(id: String): T?
    fun putEntry(id: String, entry: T)
}