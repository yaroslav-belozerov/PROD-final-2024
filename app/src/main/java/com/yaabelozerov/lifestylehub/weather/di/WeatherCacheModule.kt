package com.yaabelozerov.lifestylehub.weather.di

import com.yaabelozerov.lifestylehub.weather.data.local.model.CacheRegistry
import com.yaabelozerov.lifestylehub.weather.data.local.model.WeatherDataCacheEntry
import com.yaabelozerov.lifestylehub.weather.data.local.source.WeatherCacheRegistryImpl
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import dagger.Binds
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