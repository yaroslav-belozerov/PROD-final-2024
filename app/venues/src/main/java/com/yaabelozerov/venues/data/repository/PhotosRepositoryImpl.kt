package com.yaabelozerov.venues.data.repository

import android.util.Log
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.venues.BuildConfig
import com.yaabelozerov.venues.data.remote.foursquare.mapper.FsqPhotosToStringMapper
import com.yaabelozerov.venues.data.remote.foursquare.source.FsqPhotosApi
import com.yaabelozerov.venues.domain.repository.PhotosRepository
import retrofit2.await
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(private val fsqPhotosApi: FsqPhotosApi): PhotosRepository {
    override suspend fun getPhotos(fsqId: String): Resource<List<String>> {
        Log.i("getPhotos", "fsqId: $fsqId")
        return try {
            val mapper = FsqPhotosToStringMapper()
            val dto = fsqPhotosApi.getPhotos(
                fsqId = fsqId, apiKey = BuildConfig.FSQ_PLACES_API_KEY
            ).await()
            val data = dto.map { mapper.mapToDomainModel(it) }
            Resource.Success(data = data)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Unexpected error")
        }
    }
}