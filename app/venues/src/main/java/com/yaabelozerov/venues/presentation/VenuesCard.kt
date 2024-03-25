@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)

package com.yaabelozerov.venues.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.common.presentation.ShimmerSpacer
import com.yaabelozerov.venues.R
import com.yaabelozerov.venues.domain.model.VenueData

@Composable
fun VenueCardSkeleton(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    details: @Composable (() -> Unit)?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .then(modifier),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (image != null) {
                image()
                Spacer(modifier = Modifier.height(8.dp))
            }
            title?.invoke()
            details?.invoke()
        }
    }
}

@Composable
fun VenueCardDetails(
    address: String,
    isClosed: Boolean,
    proximity: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.icons8_location),
            contentDescription = "location icon",
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = address)
    }
    Row {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.icons8_map),
                contentDescription = "map icon",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$proximity м")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = if (!isClosed) "Открыто" else "Закрыто")
    }
}

@Composable
fun VenueCard(
    modifier: Modifier = Modifier, data: VenueData
) {
//    Crossfade(targetState = state, modifier = modifier) { targetState ->
//        if (targetState.error == null) {
//            if (!targetState.isLoading && targetState.venues.isNotEmpty()) {
//                val venue = targetState.venues[index]
    VenueCardSkeleton(title = {
        Text(
            text = data.name,
            fontSize = 24.sp,
        )
    }, image = {
        if (data.photos.isNotEmpty()) {
            GlideImage(
                model = data.photos.first(),
                contentDescription = "venue image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .clip(shape = CardDefaults.shape),
                loading = placeholder(R.drawable.landscape_placeholder),
                contentScale = ContentScale.Crop,
            )
        }
    }, details = {
        VenueCardDetails(
            address = data.address,
            isClosed = data.isClosed,
            proximity = data.distance,
        )
    })
//            } else {
//                LoadingVenueCard()
//            }
//        } else {
//            VenueCardSkeleton(
//                title = {
//                    Text(
//                        text = "${targetState.error}",
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                },
//                image = null,
//                details = null,
//            )
//        }
//    }
}

@Composable
@Preview
fun LoadingVenueCard(modifier: Modifier = Modifier) {
    VenueCardSkeleton(
        modifier = modifier,
        title = { ShimmerSpacer(width = 384f, height = 32f) },
        image = null,
        details = {
            Spacer(modifier = Modifier.height(4.dp))
            ShimmerSpacer(width = 96f, height = 16f)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                ShimmerSpacer(width = 64f, height = 16f)
                Spacer(modifier = Modifier.weight(1f))
                ShimmerSpacer(width = 64f, height = 16f)
            }
        },
    )
}

@Composable
fun ErrorVenueCard(modifier: Modifier = Modifier, error: String) {
    VenueCardSkeleton(
        modifier = modifier,
        title = {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
            )
        },
        image = null,
        details = null,
    )
}