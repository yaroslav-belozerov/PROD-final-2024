package com.yaabelozerov.location.domain

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}