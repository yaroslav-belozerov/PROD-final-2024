package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yaabelozerov.venues.presentation.ErrorVenueCard
import com.yaabelozerov.venues.presentation.LoadingVenueCard
import com.yaabelozerov.venues.presentation.VenueCard
import com.yaabelozerov.venues.presentation.VenuesState
import com.yaabelozerov.weather.presentation.WeatherState
import com.yaabelozerov.weather.presentation.ui.WeatherCard

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
                Crossfade(targetState = weatherState) { WeatherCard(state = it) }
            }
            if (venuesState.error == null) {
                if (!venuesState.isLoading) {
                    items(venuesState.venues.size) { index ->
                        VenueCard(
                            data = venuesState.venues[index],
                        )
                    }
                } else {
                    item { LoadingVenueCard() }
                }
            } else {
                item {
                    ErrorVenueCard(
                        error = venuesState.error.toString(),
                    )
                }
            }
        }
    }
}
