package com.yaabelozerov.venues.domain.repository

import com.yaabelozerov.common.domain.Resource

interface PhotosRepository {
    suspend fun getPhotos(fsqId: String): Resource<List<String>>
}
