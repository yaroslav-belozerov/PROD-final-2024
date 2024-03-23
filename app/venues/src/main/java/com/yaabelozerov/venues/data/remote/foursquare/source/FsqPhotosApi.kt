package com.yaabelozerov.venues.data.remote.foursquare.source

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaabelozerov.venues.data.remote.foursquare.FsqPhotosDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FsqPhotosApi : FsqPhotosService {
    override fun getPhotos(
        apiKey: String,
        fsqId: String,
    ): Call<List<FsqPhotosDTO>> {
        val moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()

        val retrofit =
            Retrofit.Builder().baseUrl("https://api.foursquare.com/v3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        val call: Call<List<FsqPhotosDTO>> =
            retrofit.create(FsqPhotosService::class.java).getPhotos(
                apiKey = apiKey,
                fsqId = fsqId,
            )

        call.clone().enqueue(
            object : Callback<List<FsqPhotosDTO>?> {
                override fun onResponse(
                    call: Call<List<FsqPhotosDTO>?>,
                    response: Response<List<FsqPhotosDTO>?>,
                ) {
                    Log.i("images_onResponse", response.raw().toString())
                }

                override fun onFailure(
                    call: Call<List<FsqPhotosDTO>?>,
                    t: Throwable,
                ) {
                    t.printStackTrace()
                }
            },
        )

        return call
    }
}
