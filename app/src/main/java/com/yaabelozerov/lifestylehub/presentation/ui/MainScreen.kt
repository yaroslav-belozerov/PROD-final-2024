package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yaabelozerov.venues.presentation.VenueCardSingle
import com.yaabelozerov.venues.presentation.VenuesCardViewModel
import com.yaabelozerov.weather.presentation.WeatherCardSingle
import com.yaabelozerov.weather.presentation.WeatherCardViewModel

@Composable
fun MainScreen() {
    val weatherCardViewModel: WeatherCardViewModel = viewModel()
    val venuesCardViewModel: VenuesCardViewModel = viewModel()

    val weather = weatherCardViewModel.weather
    val venues = venuesCardViewModel.venues
    venuesCardViewModel.loadVenues()
    weatherCardViewModel.loadWeatherInfo()

    MaterialTheme {
        LazyColumn(Modifier.wrapContentHeight()) {
            item {
                WeatherCardSingle(state = weather.value)
            }
            items(maxOf(venues.value.venues.size, 5)) { index ->
                VenueCardSingle(state = venues.value, index = index)
            }
        }
    }
}
