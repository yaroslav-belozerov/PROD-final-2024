package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yaabelozerov.venues.presentation.VenueCardSingle
import com.yaabelozerov.venues.presentation.VenuesState
import com.yaabelozerov.weather.presentation.WeatherCard
import com.yaabelozerov.weather.presentation.WeatherState

@Composable
fun MainScreen(
    weatherState: WeatherState,
    venuesState: VenuesState,
) {
    MaterialTheme {
        LazyColumn(
            Modifier
                .wrapContentHeight(),
        ) {
            item {
                WeatherCard(state = weatherState)
            }
            items(maxOf(venuesState.venues.size, 5)) { index ->
                VenueCardSingle(state = venuesState, index = index)
            }
        }
    }
}
