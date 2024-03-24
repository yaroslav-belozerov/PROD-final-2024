package com.yaabelozerov.venues.data.remote.foursquare.source

import com.yaabelozerov.venues.data.remote.foursquare.FsqPlacesDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FsqPlacesApiService {
    @GET("search")
    suspend fun placesByCoordinates(
        @Header("Authorization") apiKey: String,
        @Query("ll") coordinates: String,
        @Query("radius") radius: Int,
        @Query("fields") fields: String,
    ): FsqPlacesDTO
}
