package com.yaabelozerov.venues.data.remote.foursquare.mapper

import android.util.Log
import com.yaabelozerov.common.domain.DomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.model.Result
import com.yaabelozerov.venues.domain.model.VenueData

class FsqWeatherToDomainMapper : DomainMapper<Result, VenueData> {
    override fun mapToDomainModel(obj: Result): VenueData {
        Log.d("MAPPER", obj.geocodes?.main.toString())
        return VenueData(
            id = obj.fsqId.toString(),
            name = obj.name.toString(),
            categories = obj.categories?.map { it.shortName!! } ?: listOf(),
            distance = obj.distance.toString(),
            isClosed =
                when (obj.closedBucket) {
                    "VeryLikelyOpen", "LikelyOpen" -> true
                    else -> false
                },
            address = obj.location?.formattedAddress.toString(),
            photos = obj.photos?.map { it.prefix + "original" + it.suffix } ?: listOf(),
            latlon = obj.geocodes?.main?.latitude?.toInt().toString() + "," + obj.geocodes?.main?.longitude?.toInt().toString(),
            timeStamp = System.currentTimeMillis(),
            isFavourite = false,
        )
    }
}
