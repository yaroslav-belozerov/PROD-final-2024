package com.yaabelozerov.venues.data.remote.mapper

import com.yaabelozerov.common.domain.DomainMapper
import com.yaabelozerov.venues.data.remote.model.Result
import com.yaabelozerov.venues.domain.model.VenueData

class FsqWeatherToDomainMapper : DomainMapper<Result, VenueData> {

    override fun mapToDomainModel(obj: Result): VenueData {
        return VenueData(
            id = obj.fsqId.toString(),
            name = obj.name.toString(),
            distance = obj.distance.toString(),
            isClosed = when (obj.closedBucket) {
                "VeryLikelyOpen", "LikelyOpen" -> true
                else -> false
            },
            address = obj.location?.formattedAddress.toString()
        )
    }
}