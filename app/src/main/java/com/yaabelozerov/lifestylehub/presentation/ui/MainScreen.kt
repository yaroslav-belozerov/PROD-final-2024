package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yaabelozerov.venues.presentation.VenueCardSingle
import com.yaabelozerov.weather.presentation.WeatherCardSingle

@Composable
fun MainScreen(state: MainScreenState) {
    MaterialTheme {
        LazyColumn(
            Modifier
                .wrapContentHeight(),
        ) {
            item {
                WeatherCardSingle(state = state.weatherState)
            }
            items(maxOf(state.venuesState.venues.size, 5)) { index ->
                VenueCardSingle(state = state.venuesState, index = index)
            }
        }
    }
}
