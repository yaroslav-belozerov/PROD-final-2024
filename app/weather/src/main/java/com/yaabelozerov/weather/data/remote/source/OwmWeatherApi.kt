package com.yaabelozerov.weather.data.remote.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaabelozerov.weather.data.remote.OwmWeatherDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface OnWeatherApiReceivedCallback {
    fun onSuccess(dto: OwmWeatherDTO)
    fun onError(throwable: Throwable)
}

class OwmWeatherApi : OwmWeatherService {
    override fun weatherByCoordinates(
        latitude: Double, lontitude: Double, lang: String?, apiKey: String?, units: String?
    ): Call<OwmWeatherDTO> {
        val moshi = Moshi.Builder() // adapter
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        val call: Call<OwmWeatherDTO> = retrofit.create(OwmWeatherService::class.java)
            .weatherByCoordinates(latitude, lontitude, lang, apiKey, units)

        call.clone().enqueue(object : Callback<OwmWeatherDTO?> {
            override fun onResponse(
                call: Call<OwmWeatherDTO?>, response: Response<OwmWeatherDTO?>
            ) {
                val dto: OwmWeatherDTO? = response.body()
            }

            override fun onFailure(call: Call<OwmWeatherDTO?>, throwable: Throwable) {
                println(throwable)
            }
        })

        return call
    }
}

