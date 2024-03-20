@file:OptIn(ExperimentalGlideComposeApi::class)

package com.yaabelozerov.lifestylehub.weather.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.lifestylehub.R
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData

@Composable
fun WeatherCard(
    state: WeatherState = WeatherState()
) {

    Crossfade(targetState = state, label = "card crossfade") {
        if (it.error == null) {
            if (it.isLoading) {
                LoadingWeatherCard()
            } else {
                if (it.weatherData != null) {
                    FilledWeatherCard(data = it.weatherData)
                } else {
                    ErrorWeatherCard(error = "No data")
                }
            }
        } else {
            ErrorWeatherCard(error = it.error)
        }
    }
}


@Composable
fun FilledWeatherCard(data: WeatherData) {
    CardSkeleton(place = { Text(text = data.place) }, image = {
        GlideImage(
            model = data.iconUrl, contentDescription = "None", modifier = Modifier.size(64.dp)
        )
    }, data = {
        Column {
            Text(text = data.temperature, fontSize = 20.sp)
            Column {
                Text(text = "Feels like ${data.feelsLike}", fontSize = 12.sp)
                Text(text = data.description, fontSize = 12.sp)
            }
        }
    })
}

@Composable
fun LoadingWeatherCard() {
    CardSkeleton(place = {
        Column {
            ShimmerSpacer(width = 80f, height = 24f)
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
    CardSkeleton(place = null, image = null, data = {
        Text(text = error)
    })
}

fun Modifier.shimmer(colors: List<Color>): Modifier = composed {
    var size by remember {
        mutableStateOf(
            IntSize.Zero
        )
    }
    val transition = rememberInfiniteTransition(label = "Shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000)
        ),
        label = "startOffsetX"
    )

    background(
        brush = Brush.horizontalGradient(
            colors = colors, startX = startOffsetX, endX = startOffsetX + size.width.toFloat()
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

@Composable
fun CardSkeleton(
    place: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    data: @Composable (() -> Unit)?
) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(128.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            place?.invoke()
            Row(verticalAlignment = Alignment.CenterVertically) {
                image?.invoke()
                data?.invoke()
            }
        }
    }
}

@Composable
fun ShimmerSpacer(width: Float, height: Float) {
    Spacer(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .shimmer(
                colors = listOf(
                    Color.LightGray.copy(alpha = 0.9f),
                    Color.LightGray.copy(alpha = 0.3f),
                    Color.LightGray.copy(alpha = 0.3f),
                    Color.LightGray.copy(alpha = 0.9f)
                )
            )
    )
}