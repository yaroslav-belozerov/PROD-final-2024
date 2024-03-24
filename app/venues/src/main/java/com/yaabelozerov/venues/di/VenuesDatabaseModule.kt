package com.yaabelozerov.venues.di

import android.content.Context
import androidx.room.Room
import com.yaabelozerov.venues.data.local.room.VenuesDao
import com.yaabelozerov.venues.data.local.room.VenuesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VenuesDatabaseModule {
    @Provides
    @Singleton
    fun provideVenuesDatabase(
        @ApplicationContext context: Context,
    ): VenuesDatabase {
        return Room.databaseBuilder(
            context = context,
            VenuesDatabase::class.java,
            VenuesDatabase.DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideVenuesDao(venuesDatabase: VenuesDatabase): VenuesDao {
        return venuesDatabase.dao
    }
}
