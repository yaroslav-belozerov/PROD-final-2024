package com.yaabelozerov.weather.data.remote.util

sealed class Constants {
    companion object {
        const val OWM_ICON_URL = "https://openweathermap.org/img/wn/"
        const val OWM_ICON_PARAMETERS = "@4x.png"
        const val OWM_ICON_PLACEHOLDER = "10d"
    }
}