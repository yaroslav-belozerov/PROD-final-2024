package com.yaabelozerov.lifestylehub.weather.di

import com.yaabelozerov.lifestylehub.weather.data.remote.source.OwmWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {
    @Provides
    @Singleton
    fun provideOwmWeatherApi(): OwmWeatherApi {
        return OwmWeatherApi()
    }
}