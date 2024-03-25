@file:OptIn(ExperimentalGlideComposeApi::class)

package com.yaabelozerov.weather.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.common.presentation.Constants
import com.yaabelozerov.common.presentation.ShimmerSpacer
import com.yaabelozerov.weather.R
import com.yaabelozerov.weather.domain.model.WeatherData

@Composable
fun WeatherCard(modifier: Modifier = Modifier, state: WeatherState) {
//    Crossfade(targetState = state, label = "card crossfade") {
        if (state.error == null) {
            if (state.isLoading) {
                LoadingWeatherCard()
            } else {
                if (state.weatherData != null) {
                    FilledWeatherCard(data = state.weatherData)
                } else {
                    LoadingWeatherCard()
                }
            }
        } else {
            ErrorWeatherCard(error = state.error)
        }
//    }
}

@Composable
fun FilledWeatherCard(data: WeatherData) {
    WeatherCardSkeleton(place = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = data.place, fontSize = Constants.Fonts.medium)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.description, fontSize = Constants.Fonts.small)
        }
    }, image = {
        GlideImage(
            model = data.iconUrl,
            contentDescription = "None",
            modifier =
                Modifier.size(46.dp),
            loading = placeholder(R.drawable.weather_icon_placeholder),
        )
    }, data = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = data.temperature + Constants.DEGREE_SYMBOL + "C",
                fontSize = Constants.Fonts.large,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.Center) {
                if (data.tempMin != data.temperature) {
                    Row(Modifier.wrapContentHeight()) {
                        if (data.tempMin != data.tempMax) {
                            Text(
                                text = "From ${data.tempMin}${Constants.DEGREE_SYMBOL}",
                                fontSize = Constants.Fonts.small,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = "to ${data.tempMax}${Constants.DEGREE_SYMBOL}",
                            fontSize = Constants.Fonts.small,
                        )
                    }
                }
                Text(
                    text = "Feels like ${data.feelsLike}${Constants.DEGREE_SYMBOL}",
                    fontSize = Constants.Fonts.small,
                )
            }
        }
    })
}

@Composable
@Preview
fun LoadingWeatherCard() {
    WeatherCardSkeleton(place = {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ShimmerSpacer(width = 96f, height = 24f)
                Spacer(modifier = Modifier.width(8.dp))
                ShimmerSpacer(width = 80f, height = 24f)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }, image = {
        ShimmerSpacer(width = 48f, height = 48f)
    }, data = {
        Row {
            Spacer(modifier = Modifier.width(8.dp))
            Row {
                Row {
                    ShimmerSpacer(width = 80f, height = 48f)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column {
                    ShimmerSpacer(width = 96f, height = 20f)
                    Spacer(modifier = Modifier.height(8.dp))
                    ShimmerSpacer(width = 64f, height = 20f)
                }
            }
        }
    })
}

@Composable
@Preview
fun FilledWeatherCardPreview() {
    val data = WeatherData("Зюзино", "Пасмурно", "", "-10", "-100", "+100", "-2", "100", "100")
    FilledWeatherCard(data = data)
}

@Composable
fun ErrorWeatherCard(modifier: Modifier = Modifier, error: String) {
    WeatherCardSkeleton(modifier = modifier, place = null, image = null, data = {
        Text(
            text = error,
            fontSize = Constants.Fonts.small,
            color = MaterialTheme.colorScheme.error,
        )
    })
}

@Composable
@Preview
fun ErrorWeatherCardPreview() {
    ErrorWeatherCard(
        error =
            "Unknown error, please try again or contact support. Lorem ipsum dolor sit amet " +
                "consectetur adipiscing elit. Donec ut nunc et sem ultrices auctor. ",
    )
}

@Composable
fun WeatherCardSkeleton(
    modifier: Modifier = Modifier,
    place: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    data: @Composable (() -> Unit)?,
) {
    ElevatedCard(
        modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth().then(modifier),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(16.dp),
        ) {
            place?.invoke()
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                image?.invoke()
                data?.invoke()
            }
        }
    }
}
