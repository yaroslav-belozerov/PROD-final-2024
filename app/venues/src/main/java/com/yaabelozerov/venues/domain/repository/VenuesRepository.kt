package com.yaabelozerov.venues.domain.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.domain.model.VenueData

interface VenuesRepository {
    suspend fun getVenues(
        lat: Double,
        lon: Double,
        radius: Int,
    ): Resource<List<VenueData>>
}
