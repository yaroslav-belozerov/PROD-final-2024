package com.yaabelozerov.venues.data.remote.foursquare.mapper

import com.yaabelozerov.venues.data.remote.foursquare.model.Category
import com.yaabelozerov.venues.data.remote.foursquare.model.Geocodes
import com.yaabelozerov.venues.data.remote.foursquare.model.Location
import com.yaabelozerov.venues.data.remote.foursquare.model.Main
import com.yaabelozerov.venues.data.remote.foursquare.model.Photo
import com.yaabelozerov.venues.data.remote.foursquare.model.Result
import com.yaabelozerov.venues.domain.model.VenueData
import org.junit.Assert
import org.junit.Test

class FsqPlacesToDomainMapperTest {
    private val mapper = FsqPlacesToDomainMapper()

    @Test
    fun mapperTest() {
        Assert.assertEquals(
            VenueData(
                id = "2031",
                name = "name",
                categories = listOf("category"),
                distance = "100",
                address = "location",
                isClosed = false,
                photos = listOf("prefixoriginalsuffix"),
                latlon = "11,0",
                timeStamp = 0L,
                isFavourite = false,
            ),
            mapper.mapToDomainModel(
                Result(
                    fsqId = "2031",
                    name = "name",
                    location = Location(formattedAddress = "location"),
                    categories = listOf(Category("category")),
                    closedBucket = "VeryLikelyOpen",
                    distance = 100,
                    geocodes = Geocodes(main = Main(latitude = 11.11, longitude = 00.01)),
                    photos = listOf(Photo(prefix = "prefix", suffix = "suffix")),
                ),
            ).copy(timeStamp = 0L),
        )
    }

    @Test
    fun mapperTestNull() {
        Assert.assertEquals(
            VenueData(
                id = "2021",
                name = "",
                categories = listOf("category"),
                distance = "100",
                address = "",
                isClosed = true,
                photos = listOf("prefixoriginalsuffix"),
                latlon = "11,0",
                timeStamp = 0L,
                isFavourite = false,
            ),
            mapper.mapToDomainModel(
                Result(
                    fsqId = "2021",
                    name = null,
                    location = Location(formattedAddress = null),
                    categories = listOf(Category("category")),
                    closedBucket = null,
                    distance = 100,
                    geocodes = Geocodes(main = Main(latitude = 11.11, longitude = 00.01)),
                    photos = listOf(Photo(prefix = "prefix", suffix = "suffix")),
                ),
            ).copy(timeStamp = 0L),
        )
    }

    @Test
    fun mapperTestNullException1() {
        Assert.assertThrows(
            NullPointerException::class.java,
        ) {
            mapper.mapToDomainModel(
                Result(
                    fsqId = null,
                    name = null,
                    location = Location(formattedAddress = null),
                    categories = listOf(Category("category")),
                    closedBucket = null,
                    distance = 100,
                    geocodes = Geocodes(main = Main(latitude = 11.11, longitude = 00.01)),
                    photos = listOf(Photo(prefix = "prefix", suffix = "suffix")),
                ),
            )
        }
    }

    @Test
    fun mapperTestNullException2() {
        Assert.assertThrows(
            NullPointerException::class.java,
        ) {
            mapper.mapToDomainModel(
                Result(
                    fsqId = "1234",
                    name = null,
                    location = Location(formattedAddress = null),
                    categories = listOf(Category("category")),
                    closedBucket = null,
                    distance = 100,
                    geocodes = Geocodes(main = Main(latitude = null, longitude = 00.01)),
                    photos = listOf(Photo(prefix = "prefix", suffix = "suffix")),
                ),
            )
        }
    }
}
