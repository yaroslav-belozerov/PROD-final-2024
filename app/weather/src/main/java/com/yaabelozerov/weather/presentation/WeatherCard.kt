@file:OptIn(ExperimentalGlideComposeApi::class)

package com.yaabelozerov.weather.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.yaabelozerov.common.presentation.Constants
import com.yaabelozerov.common.presentation.ShimmerSpacer
import com.yaabelozerov.weather.domain.model.WeatherData

@Composable
fun WeatherCardSingle(state: WeatherState = WeatherState()) {
    Crossfade(targetState = state, label = "card crossfade") {
        if (it.error == null) {
            if (it.isLoading) {
                LoadingWeatherCard()
            } else {
                if (state.weatherData != null) {
                    WeatherCard(data = state.weatherData)
                } else {
                    LoadingWeatherCard()
                }
            }
        } else {
            ErrorWeatherCard(error = it.error)
        }
    }
}

@Composable
fun WeatherCard(data: WeatherData) {
    WeatherCardSkeleton(place = { Text(text = data.place) }, image = {
        GlideImage(
            model = data.iconUrl,
            contentDescription = "None",
            modifier = Modifier.size(64.dp),
        )
    }, data = {
        Column {
            Text(text = data.temperature, fontSize = Constants.Fonts.medium)
            Column {
                Text(text = "Feels like ${data.feelsLike}", fontSize = Constants.Fonts.small)
                Text(text = data.description, fontSize = Constants.Fonts.small)
            }
        }
    })
}

@Composable
fun LoadingWeatherCard() {
    WeatherCardSkeleton(place = {
        Column {
            ShimmerSpacer(width = 128f, height = 32f)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }, image = {
        ShimmerSpacer(width = 64f, height = 64f)
    }, data = {
        Row {
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                ShimmerSpacer(width = 32f, height = 24f)
                Spacer(modifier = Modifier.height(4.dp))
                ShimmerSpacer(width = 128f, height = 16f)
                Spacer(modifier = Modifier.height(4.dp))
                ShimmerSpacer(width = 128f, height = 16f)
            }
        }
    })
}

@Composable
fun ErrorWeatherCard(error: String) {
    WeatherCardSkeleton(place = null, image = null, data = {
        Text(text = error)
    })
}

@Composable
fun WeatherCardSkeleton(
    place: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    data: @Composable (() -> Unit)?,
) {
    ElevatedCard(
        modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(128.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            place?.invoke()
            Row(verticalAlignment = Alignment.CenterVertically) {
                image?.invoke()
                data?.invoke()
            }
        }
    }
}
