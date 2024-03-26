package com.yaabelozerov.venues.data.util

sealed class Constants {
    companion object {
        const val CACHED_HRS: Long = 6
        const val FSQ_IMG_SIZE: String = "original"
        const val FSQ_BASE_PLACES_URL: String = "https://api.foursquare.com/v3/places/"
        const val FSQ_RADIUS: Int = 1000
    }
}
