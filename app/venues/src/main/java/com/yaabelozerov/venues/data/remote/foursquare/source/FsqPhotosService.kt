package com.yaabelozerov.venues.data.remote.foursquare.source

import com.yaabelozerov.venues.data.remote.foursquare.FsqPhotosDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FsqPhotosService {
    @GET("places/{fsq_id}/photos")
    fun getPhotos(
        @Header("Authorization") apiKey: String,
        @Path(value = "fsq_id") fsqId: String
    ): Call<List<FsqPhotosDTO>>
}