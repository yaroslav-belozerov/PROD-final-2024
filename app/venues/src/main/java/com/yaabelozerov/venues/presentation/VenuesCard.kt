@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)

package com.yaabelozerov.venues.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.common.presentation.ShimmerSpacer
import com.yaabelozerov.venues.R
import com.yaabelozerov.venues.domain.model.VenueData

@Composable
fun VenuesCard(state: VenuesState = VenuesState()) {
    Crossfade(targetState = state, label = "card crossfade") {
        if (it.error == null) {
            if (!it.isLoading && it.venues.isNotEmpty()) {
                FilledVenueCard(venues = state.venues)
            } else {
                LoadingVenueCard()
            }
        } else {
            VenueCardSkeleton(
                title = { Text(text = "Unexpected error: ${it.error}") },
                image = null,
                details = null,
            )
        }
    }
}

@Composable
fun LoadingVenueCard() {
    LazyColumn {
        items(5) {
            VenueCardSkeleton(
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
    }
}

@Composable
fun VenueCardSkeleton(
    title: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    details: @Composable (() -> Unit)?,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            image?.invoke()
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
fun FilledVenueCard(venues: List<VenueData>) {
    LazyColumn {
        items(venues.size) { index ->
            VenueCardSkeleton(title = {
                Text(
                    text = venues[index].name,
                    fontSize = 24.sp,
                )
            }, image = {
                if (venues[index].photos.isNotEmpty()) {
                    GlideImage(
                        model = venues[index].photos.first(),
                        contentDescription = "venue image",
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(128.dp)
                                .clip(shape = CardDefaults.shape),
                        loading = placeholder(R.drawable.landscape_placeholder),
                        contentScale = ContentScale.Crop,
                    )
                }
            }, details = {
                VenueCardDetails(
                    address = venues[index].address,
                    isClosed = venues[index].isClosed,
                    proximity = venues[index].distance,
                )
            })
        }
    }
}

@Composable
fun VenueCardSingle(
    state: VenuesState,
    index: Int,
) {
    Crossfade(targetState = state) { targetState ->
        if (targetState.error == null) {
            if (!targetState.isLoading && targetState.venues.isNotEmpty()) {
                val venue = targetState.venues[index]
                VenueCardSkeleton(title = {
                    Text(
                        text = venue.name,
                        fontSize = 24.sp,
                    )
                }, image = {
                    if (venue.photos.isNotEmpty()) {
                        GlideImage(
                            model = venue.photos.first(),
                            contentDescription = "venue image",
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(128.dp)
                                    .clip(shape = CardDefaults.shape),
                            loading = placeholder(R.drawable.landscape_placeholder),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }, details = {
                    VenueCardDetails(
                        address = venue.address,
                        isClosed = venue.isClosed,
                        proximity = venue.distance,
                    )
                })
            } else {
                LoadingCardSingle()
            }
        } else {
            VenueCardSkeleton(
                title = { Text(text = "Unexpected error: ${targetState.error}") },
                image = null,
                details = null,
            )
        }
    }
}

@Composable
fun LoadingCardSingle() {
    VenueCardSkeleton(
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
