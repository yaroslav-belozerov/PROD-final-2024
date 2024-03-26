package com.yaabelozerov.venues.data.repository

import android.util.Log
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.BuildConfig
import com.yaabelozerov.venues.data.local.room.VenuesDao
import com.yaabelozerov.venues.data.remote.foursquare.mapper.FsqWeatherToDomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.source.FsqPlacesApi
import com.yaabelozerov.venues.data.util.Constants
import com.yaabelozerov.venues.domain.model.VenueData
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class VenuesRepositoryImpl
    @Inject
    constructor(
        private val fsqPlacesApi: FsqPlacesApi,
        private val venuesDao: VenuesDao,
    ) : VenuesRepository {
        override fun getVenues(
            lat: Double,
            lon: Double,
            radius: Int,
        ): Flow<Resource<List<VenueData>>> =
            channelFlow {
                try {
                    val cacheInvalidBreakpoint: Long = Constants.CACHED_HRS * 60 * 60 * 1000
                    venuesDao.invalidateCachesBeforeTimestamp(cacheInvalidBreakpoint)
                    venuesDao.getByLatLonAfterTimestamp(
                        latlon = "${lat.toInt()},${lon.toInt()}",
                        timestamp = System.currentTimeMillis() - cacheInvalidBreakpoint,
                    ).collect { listOfVenues ->
                        if (listOfVenues.isNotEmpty()) {
                            Log.d("sent_from_db", listOfVenues.toString())
                            send(Resource.Success(listOfVenues))
                        } else {
                            val dto =
                                fsqPlacesApi.placesByCoordinates(
                                    apiKey = BuildConfig.FSQ_PLACES_API_KEY,
                                    coordinates = "$lat,$lon",
                                    radius = 1000,
                                    fields =
                                        listOf(
                                            "fsq_id",
                                            "closed_bucket",
                                            "categories",
                                            "distance",
                                            "location",
                                            "name",
                                            "photos",
                                            "geocodes",
                                        ).joinToString(separator = ","),
                                )
                            Log.d("sent_from_api", dto.toString())
                            val mapped =
                                dto.results!!.map {
                                    FsqWeatherToDomainMapper().mapToDomainModel(
                                        it,
                                    )
                                }
                            mapped.forEach { venuesDao.upsert(it) }
                            send(
                                Resource.Success(mapped),
                            )
                        }
                    }
                } catch (e: Exception) {
                    send(
                        Resource.Error(
                            message =
                                e.message
                                    ?: com.yaabelozerov.common.presentation.Constants.ErrorMessages.UNKNOWN,
                        ),
                    )
                }
                awaitClose()
            }
    }
