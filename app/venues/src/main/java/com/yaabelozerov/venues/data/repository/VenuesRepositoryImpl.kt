package com.yaabelozerov.venues.data.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.BuildConfig
import com.yaabelozerov.venues.data.remote.foursquare.mapper.FsqWeatherToDomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.source.FsqPlacesApi
import com.yaabelozerov.venues.domain.model.BundledVenueData
import com.yaabelozerov.venues.domain.repository.PhotosRepository
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import retrofit2.await
import javax.inject.Inject

class VenuesRepositoryImpl
    @Inject
    constructor(private val fsqPlacesApi: FsqPlacesApi, private val photosRepositoryImpl: PhotosRepository) :
    VenuesRepository {
        override suspend fun getVenues(
            lat: Double,
            lon: Double,
            radius: Int,
        ): Resource<List<BundledVenueData>> {
            return try {
                val mapper = FsqWeatherToDomainMapper()
                val dto =
                    fsqPlacesApi.placesByCoordinates(
                        apiKey = BuildConfig.FSQ_PLACES_API_KEY,
                        coordinates = "$lat,$lon",
                        radius = radius,
                    ).await()
                val data = dto.results!!.map { mapper.mapToDomainModel(it) }
                val bundledData = data.map { BundledVenueData(it, photosRepositoryImpl.getPhotos(it.id).data ?: emptyList()) }
                Resource.Success(data = bundledData)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: "Unexpected error")
            }
        }
    }
