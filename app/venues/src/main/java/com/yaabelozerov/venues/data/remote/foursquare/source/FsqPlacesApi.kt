package com.yaabelozerov.venues.data.remote.foursquare.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaabelozerov.venues.data.remote.foursquare.FsqPlacesDTO
import com.yaabelozerov.venues.data.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FsqPlacesApi : FsqPlacesApiService {
    override suspend fun placesByCoordinates(
        apiKey: String,
        coordinates: String,
        radius: Int,
        fields: String,
    ): FsqPlacesDTO {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val retrofit =
            Retrofit.Builder().baseUrl(Constants.FSQ_BASE_PLACES_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

        return retrofit.create(FsqPlacesApiService::class.java).placesByCoordinates(
            apiKey = apiKey,
            coordinates = coordinates,
            radius = radius,
            fields = fields,
        )
    }
}
