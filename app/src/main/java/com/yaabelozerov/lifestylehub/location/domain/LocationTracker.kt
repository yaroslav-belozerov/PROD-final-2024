package com.yaabelozerov.lifestylehub.location.domain

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}