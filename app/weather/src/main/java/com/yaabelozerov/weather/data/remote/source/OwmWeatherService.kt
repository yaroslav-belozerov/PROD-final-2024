package com.yaabelozerov.weather.data.remote.source

import com.yaabelozerov.weather.BuildConfig
import com.yaabelozerov.weather.data.remote.OwmWeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OwmWeatherService {
    @GET("weather")
    fun weatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") lontitude: Double,
        @Query("lang") lang: String? = "en",
        @Query("appid") apiKey: String? = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("units") units: String? = "metric",
    ): Call<OwmWeatherDTO>
}
