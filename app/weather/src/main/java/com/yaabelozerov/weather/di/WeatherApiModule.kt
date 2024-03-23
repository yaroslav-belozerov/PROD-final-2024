package com.yaabelozerov.weather.di

import com.yaabelozerov.weather.data.remote.source.OwmWeatherApi
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
