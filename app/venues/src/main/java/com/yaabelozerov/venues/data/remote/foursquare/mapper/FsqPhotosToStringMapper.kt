package com.yaabelozerov.venues.data.remote.foursquare.mapper

import com.yaabelozerov.common.domain.DomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.FsqPhotosDTO

class FsqPhotosToStringMapper : DomainMapper<FsqPhotosDTO, String> {
    override fun mapToDomainModel(obj: FsqPhotosDTO): String {
        return obj.prefix + "original" + obj.suffix
    }
}
