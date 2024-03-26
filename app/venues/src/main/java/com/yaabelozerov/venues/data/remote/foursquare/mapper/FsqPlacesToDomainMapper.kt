package com.yaabelozerov.venues.data.remote.foursquare.mapper

import com.yaabelozerov.common.domain.DomainMapper
import com.yaabelozerov.venues.data.remote.foursquare.model.Result
import com.yaabelozerov.venues.data.util.Constants
import com.yaabelozerov.venues.domain.model.VenueData

class FsqPlacesToDomainMapper : DomainMapper<Result, VenueData> {
    override fun mapToDomainModel(obj: Result): VenueData {
        return VenueData(
            id = obj.fsqId!!,
            name = obj.name ?: "",
            categories = obj.categories?.map { it.shortName!! } ?: listOf(),
            distance = obj.distance?.toString() ?: "",
            isClosed =
                when (obj.closedBucket) {
                    "VeryLikelyOpen", "LikelyOpen" -> false
                    else -> true
                },
            address = obj.location?.formattedAddress ?: "",
            photos = obj.photos?.map { it.prefix + Constants.FSQ_IMG_SIZE + it.suffix } ?: listOf(),
            latlon = obj.geocodes!!.main!!.latitude!!.toInt().toString() + "," + obj.geocodes!!.main!!.longitude!!.toInt().toString(),
            timeStamp = System.currentTimeMillis(),
            isFavourite = false,
        )
    }
}
