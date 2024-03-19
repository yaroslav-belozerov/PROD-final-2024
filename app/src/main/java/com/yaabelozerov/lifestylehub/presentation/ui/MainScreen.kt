package com.yaabelozerov.lifestylehub.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherCard
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherCardViewModel

@Composable
fun MainScreen(weatherCardViewModel: WeatherCardViewModel = viewModel()) {
    weatherCardViewModel.loadWeatherInfo()
    val weatherState by weatherCardViewModel.weatherMutableStateFlow.collectAsState()

    Log.d(
        "MainScreen",
        weatherState.error.toString() + " " + weatherState.isLoading.toString() + " " + weatherState.weatherData.toString()
    )
    MaterialTheme () {
        WeatherCard(
            state = weatherState,
        )
    }
}