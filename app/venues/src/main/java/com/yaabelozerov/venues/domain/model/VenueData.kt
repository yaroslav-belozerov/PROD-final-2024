package com.yaabelozerov.venues.domain.model

data class VenueData(
    val id: String,
    val name: String,
    val distance: String,
    val address: String,
    val isClosed: Boolean,
    val photos: List<String>,
)
