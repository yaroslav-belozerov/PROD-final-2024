package com.yaabelozerov.venues.di

import com.yaabelozerov.venues.data.remote.foursquare.source.FsqPhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotosApiModule {
    @Provides
    @Singleton
    fun provideFsqImagesApi(): FsqPhotosApi {
        return FsqPhotosApi()
    }
}