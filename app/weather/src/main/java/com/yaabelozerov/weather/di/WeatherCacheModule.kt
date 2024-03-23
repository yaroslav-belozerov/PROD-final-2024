package com.yaabelozerov.weather.di

import com.yaabelozerov.weather.data.local.model.CacheRegistry
import com.yaabelozerov.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.weather.data.local.source.WeatherCacheRegistryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherCacheModule {
    @Provides
    @Singleton
    fun provideWeatherCache(): CacheRegistry<WeatherDataCacheEntry> {
        return WeatherCacheRegistryImpl()
    }
}
