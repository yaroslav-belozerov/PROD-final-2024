package com.yaabelozerov.lifestylehub.weather.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.lifestylehub.R
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData

@Composable
fun WeatherCard(
    state: WeatherState = WeatherState()
) {
    Log.i("WeatherCard_State", state.toString())

    Card(
        modifier = Modifier
            .background(Color.Green)
            .padding(16.dp)
    ) {
        if (state.error == null) {
            if (state.isLoading) {
                LoadingWeatherCard()
            } else {
                if (state.weatherData != null) {
                    FilledWeatherCard(data = state.weatherData)
                }
            }
        } else {
            ErrorWeatherCard()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadingWeatherCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface {
                GlideImage(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    model = R.drawable.owm_icon_placeholder,
                    contentDescription = "Weather icon"
                )
            }
            Text(text = "Loading")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilledWeatherCard(data: WeatherData) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface {
                GlideImage(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    model = data.iconUrl, contentDescription = "Weather icon",
                )
            }
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = data.place)
                    Text(text = data.description)
                }
                Text(text = data.temperature.toString())
            }
        }
    }
}

@Composable
fun ErrorWeatherCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .background(Color.Red)
                    .padding(16.dp)
            ) {}
            Text(text = "Error fetching weather")
        }
    }
}