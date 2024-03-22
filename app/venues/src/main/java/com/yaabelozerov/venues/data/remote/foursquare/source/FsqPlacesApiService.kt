package com.yaabelozerov.venues.data.remote.foursquare.source

import com.yaabelozerov.venues.data.remote.foursquare.FsqPlacesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FsqPlacesApiService {
    @GET("search")
    fun placesByCoordinates(
        @Header("Authorization") apiKey: String,
//        @Header("accept") accept: String,
//        @Header("Authorization: Bearer sq3Ijg8yX3C8tIQKZCzt2HLW6VJxlno5Pn3CvMFcSBgLSo=")
        @Query("ll") coordinates: String,
        @Query("radius") radius: Int,
    ): Call<FsqPlacesDTO>
}