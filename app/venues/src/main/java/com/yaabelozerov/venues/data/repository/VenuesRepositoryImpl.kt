package com.yaabelozerov.venues.data.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.BuildConfig
import com.yaabelozerov.venues.data.remote.foursquare.mapper.FsqWeatherToDomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.source.FsqPlacesApi
import com.yaabelozerov.venues.domain.model.VenueData
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VenuesRepositoryImpl
    @Inject
    constructor(
        private val fsqPlacesApi: FsqPlacesApi,
    ) : VenuesRepository {
        override suspend fun getVenues(
            lat: Double,
            lon: Double,
            radius: Int,
        ): Flow<Resource<List<VenueData>>> =
            flow {
                val venues =
                    try {
                        val dto =
                            fsqPlacesApi.placesByCoordinates(
                                apiKey = BuildConfig.FSQ_PLACES_API_KEY,
                                coordinates = "$lat,$lon",
                                radius = 1000,
                                fields =
                                    listOf(
                                        "fsq_id",
                                        "closed_bucket",
                                        "distance",
                                        "location",
                                        "name",
                                        "photos",
                                    ).joinToString(separator = ","),
                            )
                        dto.results!!.map { FsqWeatherToDomainMapper().mapToDomainModel(it) }
                    } catch (e: Exception) {
                        emit(Resource.Error(message = e.message ?: "Unknown error"))
                        return@flow
                    }

                emit(Resource.Success(data = venues))
            }
    }
