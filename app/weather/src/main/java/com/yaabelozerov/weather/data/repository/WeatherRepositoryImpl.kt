package com.yaabelozerov.weather.data.repository

import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.common.presentation.CommonConstants
import com.yaabelozerov.weather.data.remote.mapper.OwmWeatherToDomainMapper
import com.yaabelozerov.weather.data.remote.source.OwmWeatherApi
import com.yaabelozerov.weather.domain.model.WeatherData
import com.yaabelozerov.weather.domain.repository.WeatherRepository
import retrofit2.await
import javax.inject.Inject

class WeatherRepositoryImpl
    @Inject
    constructor(
        private val owmApi: OwmWeatherApi,
    ) : WeatherRepository {
        override suspend fun getWeatherData(
            lat: Double,
            lon: Double,
            lang: String?,
        ): Resource<WeatherData> {
            return try {
                val mapper = OwmWeatherToDomainMapper()
                val dto = owmApi.weatherByCoordinates(lat, lon, lang).await()
                val data = mapper.mapToDomainModel(dto)
                Resource.Success(data = data)
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: CommonConstants.ErrorMessages.UNKNOWN)
            }
        }
    }
