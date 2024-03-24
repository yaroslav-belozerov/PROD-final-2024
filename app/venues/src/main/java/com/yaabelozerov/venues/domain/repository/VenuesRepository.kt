package com.yaabelozerov.venues.domain.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.domain.model.VenueData
import kotlinx.coroutines.flow.Flow

interface VenuesRepository {
    fun getVenues(
        lat: Double,
        lon: Double,
        radius: Int,
    ): Flow<Resource<List<VenueData>>>
}
