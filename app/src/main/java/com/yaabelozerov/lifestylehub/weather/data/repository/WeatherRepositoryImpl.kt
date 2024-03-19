package com.yaabelozerov.lifestylehub.weather.data.repository

import android.util.Log
import com.yaabelozerov.lifestylehub.weather.data.local.mapper.WeatherDataCacheEntryMapper
import com.yaabelozerov.lifestylehub.weather.data.local.model.CacheRegistry
import com.yaabelozerov.lifestylehub.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.lifestylehub.weather.data.remote.OwmWeatherDTO
import com.yaabelozerov.lifestylehub.weather.data.remote.source.OwmWeatherApi
import com.yaabelozerov.lifestylehub.weather.data.remote.mapper.OwmWeatherWeatherDomainMapper
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import com.yaabelozerov.lifestylehub.weather.domain.repository.WeatherRepository
import com.yaabelozerov.lifestylehub.weather.domain.util.Resource
import retrofit2.Callback
import retrofit2.await
import retrofit2.awaitResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val owmApi: OwmWeatherApi,
    private val cacheRegistry: CacheRegistry<WeatherDataCacheEntry>
) : WeatherRepository {
    override suspend fun getWeatherData(
        lat: Double, lon: Double, lang: String?
    ): Resource<WeatherData> {
        return try {
            val cached = cacheRegistry.getEntry("${lat}_${lon}$lang")
            val cacheEntryMapper = WeatherDataCacheEntryMapper()
            if (cached == null) {
                val mapper = OwmWeatherWeatherDomainMapper()
                val dto = owmApi.weatherByCoordinates(lat, lon, lang).await()
                cacheRegistry.putEntry(
                    "${lat}_${lon}$lang", cacheEntryMapper.mapToCacheEntry(
                        mapper.mapToDomainModel(
                            dto
                        )
                    )
                )
                val data = mapper.mapToDomainModel(dto)
                Log.d("WeatherRepositoryImpl", "getWeatherData: $data")
                Resource.Success(data = data)

            } else {
                Resource.Success(cacheEntryMapper.mapToDomainModel(cached))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Unexpected error")
        }
    }
}