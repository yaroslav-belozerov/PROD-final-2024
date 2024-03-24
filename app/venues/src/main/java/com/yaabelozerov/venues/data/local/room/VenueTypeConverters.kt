package com.yaabelozerov.venues.data.local.room

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaabelozerov.venues.domain.model.VenueData

class VenueTypeConverters {
    @TypeConverter
    fun fromDataToJson(data: VenueData): String {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(VenueData::class.java).toJson(data)
    }

    @TypeConverter
    fun fromJsonToData(json: String): VenueData {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(VenueData::class.java).fromJson(json)!!
    }

    @TypeConverter
    fun fromListToJson(list: List<String>): String {
        val moshi = Moshi.Builder().build()
        return moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java)).toJson(list)
    }

    @TypeConverter
    fun fromJsonToList(json: String): List<String> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java)).fromJson(json)!!
    }
}
