package com.yaabelozerov.weather.data.repository

import android.util.Log
import com.yaabelozerov.weather.data.local.mapper.WeatherDataCacheEntryMapper
import com.yaabelozerov.weather.data.local.model.CacheRegistry
import com.yaabelozerov.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.weather.data.remote.source.OwmWeatherApi
import com.yaabelozerov.weather.data.remote.mapper.OwmWeatherWeatherDomainMapper
import com.yaabelozerov.weather.domain.model.WeatherData
import com.yaabelozerov.weather.domain.repository.WeatherRepository
import com.yaabelozerov.weather.domain.util.Resource
import retrofit2.await
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val owmApi: OwmWeatherApi,
    private val cacheRegistry: CacheRegistry<WeatherDataCacheEntry>
) : WeatherRepository {
    override suspend fun getWeatherData(
        lat: Double, lon: Double, lang: String?
    ): Resource<WeatherData> {
        return try {
            val cached = cacheRegistry.getEntry("${lat.toInt()}_${lon.toInt()}$lang")
            val cacheEntryMapper =
                com.yaabelozerov.weather.data.local.mapper.WeatherDataCacheEntryMapper()
            if (cached == null) {
                val mapper = OwmWeatherWeatherDomainMapper()
                val dto = owmApi.weatherByCoordinates(lat, lon, lang).await()
                cacheRegistry.putEntry(
                    "${lat.toInt()}_${lon.toInt()}$lang", cacheEntryMapper.mapToCacheEntry(
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