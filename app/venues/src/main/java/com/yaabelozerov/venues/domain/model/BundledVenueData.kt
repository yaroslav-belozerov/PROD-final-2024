package com.yaabelozerov.venues.domain.model

data class BundledVenueData(
    val venueData: VenueData,
    val photos: List<String>,
)
