package com.yaabelozerov.venues.di

import com.yaabelozerov.venues.data.remote.source.FsqPlacesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VenuesApiModule {
    @Provides
    @Singleton
    fun provideFsqPlacesApi(): FsqPlacesApi {
        return FsqPlacesApi()
    }
}