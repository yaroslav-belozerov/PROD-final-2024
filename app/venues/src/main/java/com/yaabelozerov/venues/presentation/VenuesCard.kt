@file:OptIn(
    ExperimentalGlideComposeApi::class,
    ExperimentalGlideComposeApi::class,
    ExperimentalMaterial3Api::class,
)

package com.yaabelozerov.venues.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.yaabelozerov.common.presentation.Constants
import com.yaabelozerov.common.presentation.ShimmerSpacer
import com.yaabelozerov.venues.R
import com.yaabelozerov.venues.domain.model.VenueData

val PREVIEW_DATA =
    VenueData(
        "12341234123",
        "Metro Cash & Carry Продуктовый магазин",
        listOf("Кафе", "Кафе", "Кафе", "Кафе", "Кафе"),
        "10000",
        "Санкт-Петербург, ул. Ленина, 1",
        true,
        listOf("", "", "", "", ""),
        "1234",
        3142355.0.toLong(),
        false,
    )

@Composable
fun VenueCardSkeleton(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    details: @Composable (() -> Unit)?,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
                .then(modifier),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (image != null) {
                image()
                Spacer(modifier = Modifier.height(4.dp))
            }
            title?.invoke()
            details?.invoke()
        }
    }
}

@Composable
fun ProximitySign(distance: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.icons8_map),
            contentDescription = "map icon",
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$distance ${Constants.DISTANCE_SYMBOL}", fontSize = Constants.Fonts.small)
    }
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun OpenClosedSign(isClosed: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.icons8_clock),
            contentDescription = "clock icon",
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text =
                if (isClosed) {
                    stringResource(R.string.closed_venue)
                } else {
                    stringResource(
                        R.string.open_venue,
                    )
                },
            fontSize = Constants.Fonts.small,
        )
    }
}

@Composable
fun AddressSign(address: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.icons8_location),
            contentDescription = "location icon",
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = address, fontSize = Constants.Fonts.small)
    }
}

@Composable
fun VenueCardModal(data: VenueData) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp)) {
        Column {
            Text(
                text = data.name,
                fontSize = Constants.Fonts.xLarge,
                lineHeight = Constants.Fonts.xLargeHeight,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                OpenClosedSign(isClosed = data.isClosed)
                Spacer(modifier = Modifier.width(8.dp))
                ProximitySign(distance = data.distance)
            }
            AddressSign(address = data.address)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(data.categories) {
                    Surface(shape = CardDefaults.shape, color = MaterialTheme.colorScheme.primaryContainer) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = it,
                            fontSize = Constants.Fonts.small,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(128.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(data.photos.size) { index ->
                GlideImage(
                    model = data.photos[index],
                    contentDescription = "venue image in popup",
                    loading = placeholder(R.drawable.landscape_placeholder),
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .height(96.dp)
                            .wrapContentWidth()
                            .clip(shape = CardDefaults.shape),
                )
            }
        }
    }
}

@Composable
@Preview
fun VenueCardModalPreview() {
    VenueCardModal(data = PREVIEW_DATA)
}

@Composable
fun VenueCard(
    modifier: Modifier = Modifier,
    data: VenueData,
) {
    var isPopupOpen by rememberSaveable { mutableStateOf(false) }

    VenueCardSkeleton(modifier = modifier.then(Modifier.clickable { isPopupOpen = true }), title = {
        Text(
            text = data.name,
            fontSize = Constants.Fonts.large,
            lineHeight = Constants.Fonts.largeHeight,
        )
    }, image = {
        if (data.photos.isNotEmpty()) {
            GlideImage(
                model = data.photos.first(),
                contentDescription = "venue image",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .heightIn(max = 192.dp)
                        .clip(shape = CardDefaults.shape),
                loading = placeholder(R.drawable.landscape_placeholder),
                contentScale = ContentScale.FillWidth,
            )
        }
    }, details = {
        Row {
            ProximitySign(distance = data.distance)
            Spacer(modifier = Modifier.width(8.dp))
            OpenClosedSign(isClosed = data.isClosed)
        }
    })
    if (isPopupOpen) {
        ModalBottomSheet(onDismissRequest = { isPopupOpen = false }) {
            VenueCardModal(data = data)
        }
    }
}

@Composable
@Preview
fun VenueCardPreview() {
    VenueCard(data = PREVIEW_DATA)
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
fun ErrorVenueCard(
    modifier: Modifier = Modifier,
    error: String,
) {
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

@Composable
@Preview
fun ErrorVenueCardPreview() {
    ErrorVenueCard(error = Constants.ErrorMessages.PLACEHOLDER)
}
