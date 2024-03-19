package com.yaabelozerov.lifestylehub.weather.di

import com.yaabelozerov.lifestylehub.weather.data.repository.WeatherRepositoryImpl
import com.yaabelozerov.lifestylehub.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}