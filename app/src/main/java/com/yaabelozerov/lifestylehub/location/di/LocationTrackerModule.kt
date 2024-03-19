package com.yaabelozerov.lifestylehub.location.di

import com.yaabelozerov.lifestylehub.location.data.DefaultLocationTracker
import com.yaabelozerov.lifestylehub.location.domain.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationTrackerModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker
}