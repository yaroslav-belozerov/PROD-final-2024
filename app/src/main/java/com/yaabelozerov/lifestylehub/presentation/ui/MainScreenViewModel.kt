package com.yaabelozerov.lifestylehub.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yaabelozerov.venues.presentation.VenuesCardViewModel
import com.yaabelozerov.weather.presentation.WeatherCardViewModel

class MainScreenViewModel : ViewModel() {
    fun loadMainScreen(
        weatherViewModel: WeatherCardViewModel,
        venuesCardViewModel: VenuesCardViewModel,
    ) {
        weatherViewModel.loadWeatherInfo()
        venuesCardViewModel.loadVenues()
        Log.i("MainScreen", "${venuesCardViewModel.venues.value} ${weatherViewModel.weather.value}")
        MainScreenState(
            venuesState = venuesCardViewModel.venues.value,
            weatherState = weatherViewModel.weather.value,
        )
    }
}
