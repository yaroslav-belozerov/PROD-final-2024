package com.yaabelozerov.weather.di

import com.yaabelozerov.weather.data.remote.mapper.OwmWeatherWeatherDomainMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherMapperModule {
    @Binds
    @Singleton
    abstract fun bindWeatherMapper(weatherMapperImpl: OwmWeatherWeatherDomainMapper): OwmWeatherWeatherDomainMapper
}