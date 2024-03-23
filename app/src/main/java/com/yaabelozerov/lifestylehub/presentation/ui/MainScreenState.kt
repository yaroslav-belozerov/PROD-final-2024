package com.yaabelozerov.lifestylehub.presentation.ui

import com.yaabelozerov.venues.presentation.VenuesState
import com.yaabelozerov.weather.presentation.WeatherState

data class MainScreenState(
    val weatherState: WeatherState = WeatherState(),
    val venuesState: VenuesState = VenuesState(),
)
