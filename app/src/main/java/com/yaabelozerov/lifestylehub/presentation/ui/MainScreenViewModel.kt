package com.yaabelozerov.lifestylehub.presentation.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yaabelozerov.venues.presentation.VenuesCardViewModel
import com.yaabelozerov.weather.presentation.WeatherCardViewModel

class MainScreenViewModel : ViewModel() {
    private val _main = mutableStateOf(MainScreenState())
    val main: State<MainScreenState> = _main

    fun loadMainScreen(
        weatherViewModel: WeatherCardViewModel,
        venuesCardViewModel: VenuesCardViewModel,
    ) {
        Log.i("Function Call", "loadMainScreen")
        weatherViewModel.loadWeatherInfo()
        venuesCardViewModel.loadVenues()
        _main.value =
            _main.value.copy(
                weatherState = weatherViewModel.weather.value,
                venuesState = venuesCardViewModel.state.value,
            )
    }
}
