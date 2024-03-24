package com.yaabelozerov.venues.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.yaabelozerov.venues.domain.model.VenueData
import kotlinx.coroutines.flow.Flow

@Dao
interface VenuesDao {
    @Upsert
    suspend fun upsert(venuesEntity: VenueData)

    @Delete
    suspend fun delete(venuesEntity: VenueData)

    @Query("SELECT * FROM VenueData where latlon = :latlon")
    fun getByLatLon(latlon: String): Flow<List<VenueData>>

    @Query("SELECT * FROM VenueData where latlon = :latlon and timeStamp > :timestamp")
    fun getByLatLonAfterTimestampLatest(
        latlon: String,
        timestamp: Long,
    ): Flow<List<VenueData>>

    @Query("SELECT * FROM VenueData where isFavourite = 1")
    fun getFavouriteVenues(): Flow<List<VenueData>>
}
