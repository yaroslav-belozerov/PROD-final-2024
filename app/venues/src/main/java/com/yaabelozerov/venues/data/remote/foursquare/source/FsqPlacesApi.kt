package com.yaabelozerov.venues.data.remote.foursquare.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaabelozerov.venues.data.remote.foursquare.FsqPlacesDTO
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
            Retrofit.Builder().baseUrl("https://api.foursquare.com/v3/places/")
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

        return retrofit.create(FsqPlacesApiService::class.java).placesByCoordinates(
            apiKey = apiKey,
            coordinates = coordinates,
            radius = radius,
            fields = fields,
        )
    }
}
