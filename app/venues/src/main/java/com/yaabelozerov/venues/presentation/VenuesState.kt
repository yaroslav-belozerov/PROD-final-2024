package com.yaabelozerov.venues.presentation

import com.yaabelozerov.venues.domain.model.VenueData

data class VenuesState(
    val venues: List<VenueData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
