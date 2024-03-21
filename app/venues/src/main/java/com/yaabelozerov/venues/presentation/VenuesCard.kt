package com.yaabelozerov.venues.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yaabelozerov.venues.domain.model.VenueData

@Composable
@Preview
fun VenuesCard(state: VenuesState = VenuesState()) {

    if (!state.isLoading && state.error == null) {
        LazyColumn {
            items(state.venues.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp)
                        .padding(16.dp, 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) { VenuesCardItem(state.venues[index]) }
            }
        }
    }

}

@Composable
fun VenuesCardItem(venueData: VenueData) {
    Text(text = venueData.name)
}
