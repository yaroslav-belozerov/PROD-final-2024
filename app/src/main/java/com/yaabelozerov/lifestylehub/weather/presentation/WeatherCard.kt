package com.yaabelozerov.lifestylehub.weather.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.lifestylehub.R
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherCard(
    state: WeatherState = WeatherState()
) {
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
            ErrorWeatherCard(error = state.error)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilledWeatherCard(data: WeatherData) {
    EmptyWeatherCard(place = data.place, image = @Composable {
        GlideImage(
            model = data.iconUrl, contentDescription = "None", modifier = Modifier.size(64.dp)
        )
    }, data = @Composable {
        Column{
            Text(text = data.temperature, fontSize = 20.sp)
            Column {
                Text(text = "Feels like ${data.feelsLike}", fontSize = 12.sp)
                Text(text = data.description, fontSize = 12.sp)
            }
        }
    })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadingWeatherCard() {
    EmptyWeatherCard(image = @Composable {
        GlideImage(
            model = R.drawable.day, contentDescription = "None"
        )
    }, data = @Composable {
        Row {
            Text(text = "Loading...")
        }
    })
}

@Composable
fun ErrorWeatherCard(error: String) {
    EmptyWeatherCard(image = null, data = @Composable {
        Row {
            Text(text = error)
        }
    })
}

@Composable
fun EmptyWeatherCard(place: String? = null, image: @Composable (() -> Unit)?, data: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            if (place != null) Text(text = place)
            Row (verticalAlignment = Alignment.CenterVertically) {
                if (image != null) image()
                data()
            }
        }
    }
}