package com.yaabelozerov.lifestylehub.presentation.ui

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherCard
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherCardViewModel


@Composable
fun MainScreen() {
    val weatherCardViewModel: WeatherCardViewModel = viewModel()
    val state = remember {
        weatherCardViewModel.weatherMutableStateFlow
    }


    weatherCardViewModel.loadWeatherInfo()
    val weatherState by state.collectAsState()

    MaterialTheme () {
        WeatherCard(
            state = weatherState,
        )
        Log.i("MaterialTheme", "Recomposed with state: $weatherState")
    }
}