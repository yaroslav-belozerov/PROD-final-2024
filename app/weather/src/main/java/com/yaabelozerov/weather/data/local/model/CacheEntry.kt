package com.yaabelozerov.weather.data.local.model

import java.time.LocalDateTime

interface CacheEntry<T> {
    val id: String
    val expiresAt: LocalDateTime
    val data: T
}