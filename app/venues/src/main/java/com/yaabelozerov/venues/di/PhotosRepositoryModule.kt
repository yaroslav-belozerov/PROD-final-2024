package com.yaabelozerov.venues.di

import com.yaabelozerov.venues.data.repository.PhotosRepositoryImpl
import com.yaabelozerov.venues.domain.repository.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPhotosRepository(photosRepositoryImpl: PhotosRepositoryImpl): PhotosRepository
}
