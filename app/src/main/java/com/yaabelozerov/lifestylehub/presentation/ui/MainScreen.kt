package com.yaabelozerov.lifestylehub.presentation.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yaabelozerov.venues.presentation.VenuesCard
import com.yaabelozerov.venues.presentation.VenuesCardViewModel
import com.yaabelozerov.weather.presentation.WeatherCard
import com.yaabelozerov.weather.presentation.WeatherCardViewModel

@Composable
fun MainScreen() {
    val weatherCardViewModel: WeatherCardViewModel = viewModel()
    val venuesCardViewModel: VenuesCardViewModel = viewModel()
    val wState = remember {
        weatherCardViewModel.weatherMutableStateFlow
    }
    val vState = remember {
        venuesCardViewModel.venuesMutableStateFlow
    }

    weatherCardViewModel.loadWeatherInfo()
    val weatherState by wState.collectAsState()

    venuesCardViewModel.loadVenues()
    val venuesState by vState.collectAsState()

    MaterialTheme {
        WeatherCard(
            state = weatherState,
        )
        VenuesCard(state = venuesState)

        Log.i("MaterialTheme", "Recomposed with state: $weatherState")
    }
}