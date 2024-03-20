package com.yaabelozerov.lifestylehub.weather.util

import kotlin.math.sign

sealed class Constants {
    companion object {
        const val OWM_ICON_URL = "https://openweathermap.org/img/wn/"
        const val OWM_ICON_PARAMETERS = "@4x.png"
        const val OWM_ICON_PLACEHOLDER = "10d"
    }
}