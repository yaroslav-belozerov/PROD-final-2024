package com.yaabelozerov.venues.data.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.BuildConfig
import com.yaabelozerov.venues.data.remote.mapper.FsqWeatherToDomainMapper
import com.yaabelozerov.venues.data.remote.source.FsqPlacesApi
import com.yaabelozerov.venues.domain.model.VenueData
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import retrofit2.await
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(private val fsqPlacesApi: FsqPlacesApi) :
    VenuesRepository {
    override suspend fun getVenues(
        lat: Double,
        lon: Double,
        radius: Int
    ): Resource<List<VenueData>> {
        return try {
            val mapper = FsqWeatherToDomainMapper()
            val dto = fsqPlacesApi.placesByCoordinates(
                apiKey = BuildConfig.FSQ_PLACES_API_KEY,
                coordinates = "$lat,$lon", radius = radius
            ).await()
            val data = dto.results!!.map { mapper.mapToDomainModel(it) }
            Resource.Success(data = data)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Unexpected error")
        }
    }
}