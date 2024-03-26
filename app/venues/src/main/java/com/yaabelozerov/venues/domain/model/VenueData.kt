package com.yaabelozerov.venues.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VenueData(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val categories: List<String>,
    val distance: String,
    val address: String,
    val isClosed: Boolean,
    val photos: List<String>,
    val latlon: String,
    val timeStamp: Long,
    val isFavourite: Boolean,
)
