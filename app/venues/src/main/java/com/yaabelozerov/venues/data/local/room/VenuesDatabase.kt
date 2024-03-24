package com.yaabelozerov.venues.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yaabelozerov.venues.domain.model.VenueData

@TypeConverters(VenueTypeConverters::class)
@Database(
    entities = [VenueData::class],
    version = 1,
    exportSchema = false,
)
abstract class VenuesDatabase : RoomDatabase() {
    abstract val dao: VenuesDao

    companion object {
        const val DATABASE_NAME = "venues.db"
    }
}
