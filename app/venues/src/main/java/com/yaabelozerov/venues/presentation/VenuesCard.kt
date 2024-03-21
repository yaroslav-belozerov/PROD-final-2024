package com.yaabelozerov.venues.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.yaabelozerov.venues.R
import com.yaabelozerov.venues.domain.model.VenueData

@Composable
fun VenuesCard(state: VenuesState = VenuesState()) {

    if (!state.isLoading && state.error == null) {
        LazyColumn {
            items(state.venues.size) { index ->
                VenueCardSkeleton(
                    title = { Text(text = state.venues[index].name, fontSize = 24.sp) }, image = null, details = {
                        VenueCardDetails(
                            address = state.venues[index].address,
                            isClosed = state.venues[index].isClosed,
                            proximity = state.venues[index].distance,
                        )
                    }
                )
            }
        }
    }

}

@Composable
fun VenueCardSkeleton(
    title: @Composable (() -> Unit)?,
    image: @Composable (() -> Unit)?,
    details: @Composable (() -> Unit)?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            image?.invoke()
            title?.invoke()
            details?.invoke()
        }
    }
}

@Composable
fun VenueCardDetails(address: String, isClosed: Boolean, proximity: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.icons8_location),
            contentDescription = "location icon"
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = address)
    }
    Row {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.icons8_map), contentDescription = "map icon"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "$proximity м")
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = if (!isClosed) "Открыто" else "Закрыто")
    }
}
