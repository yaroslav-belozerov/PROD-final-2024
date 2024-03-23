package com.yaabelozerov.venues.presentation

import com.yaabelozerov.venues.domain.model.BundledVenueData

data class VenuesState(
    val venues: List<BundledVenueData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
