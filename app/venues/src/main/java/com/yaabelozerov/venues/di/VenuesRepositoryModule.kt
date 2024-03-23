package com.yaabelozerov.venues.di

import com.yaabelozerov.venues.data.repository.VenuesRepositoryImpl
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VenuesRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindVenuesRepository(venueRepositoryImpl: VenuesRepositoryImpl): VenuesRepository
}
