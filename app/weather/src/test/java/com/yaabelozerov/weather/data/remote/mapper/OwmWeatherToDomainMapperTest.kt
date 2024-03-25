package com.yaabelozerov.weather.data.remote.mapper

import com.yaabelozerov.weather.data.remote.OwmWeatherDTO
import com.yaabelozerov.weather.data.remote.model.Coord
import com.yaabelozerov.weather.data.remote.model.Main
import com.yaabelozerov.weather.data.remote.model.Weather
import com.yaabelozerov.weather.domain.model.WeatherData
import org.junit.Assert
import org.junit.Test

class OwmWeatherToDomainMapperTest {
    private val mapper = OwmWeatherToDomainMapper()

    @Test
    fun domainMapperIntegerTest1() {
        Assert.assertEquals(
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather =
                        listOf(
                            Weather(
                                id = 100,
                                description = "Sunny",
                                icon = "01d",
                            ),
                        ),
                    main =
                        Main(
                            temp = 12.0,
                            feelsLike = 98.0,
                            tempMin = 10.0,
                            tempMax = 12.0,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = "City 17",
                ),
            ),
            WeatherData(
                place = "City 17",
                description = "Sunny",
                iconUrl = "https://openweathermap.org/img/wn/01d@4x.png",
                temperature = "+12",
                tempMin = "+10",
                tempMax = "+12",
                feelsLike = "+98",
                lat = "12.3456",
                lon = "12.3456",
            ),
        )
    }

    @Test
    fun domainMapperIntegerTest2() {
        Assert.assertEquals(
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather =
                        listOf(
                            Weather(
                                id = 100,
                                description = "Sunny",
                                icon = "01d",
                            ),
                        ),
                    main =
                        Main(
                            temp = -12.0,
                            feelsLike = -98.0,
                            tempMin = -10.0,
                            tempMax = -12.0,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = "Silent Hill",
                ),
            ),
            WeatherData(
                place = "Silent Hill",
                description = "Sunny",
                iconUrl = "https://openweathermap.org/img/wn/01d@4x.png",
                temperature = "-12",
                tempMin = "-10",
                tempMax = "-12",
                feelsLike = "-98",
                lat = "12.3456",
                lon = "12.3456",
            ),
        )
    }

    @Test
    fun domainMapperIntegerTest3() {
        Assert.assertEquals(
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather =
                        listOf(
                            Weather(
                                id = 100,
                                description = "Sunny",
                                icon = "01d",
                            ),
                        ),
                    main =
                        Main(
                            temp = 0.0,
                            feelsLike = 0.0,
                            tempMin = -0.0,
                            tempMax = 0.0,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = "Mondstadt",
                ),
            ),
            WeatherData(
                place = "Mondstadt",
                description = "Sunny",
                iconUrl = "https://openweathermap.org/img/wn/01d@4x.png",
                temperature = "0",
                tempMin = "0",
                tempMax = "0",
                feelsLike = "0",
                lat = "12.3456",
                lon = "12.3456",
            ),
        )
    }

    @Test
    fun domainMapperFracTest1() {
        Assert.assertEquals(
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather =
                        listOf(
                            Weather(
                                id = 100,
                                description = "Sunny",
                                icon = "01d",
                            ),
                        ),
                    main =
                        Main(
                            temp = 12.234,
                            feelsLike = 98.999,
                            tempMin = 101.101,
                            tempMax = 0.001,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = "City 17",
                ),
            ),
            WeatherData(
                place = "City 17",
                description = "Sunny",
                iconUrl = "https://openweathermap.org/img/wn/01d@4x.png",
                temperature = "+12",
                tempMin = "+101",
                tempMax = "0",
                feelsLike = "+98",
                lat = "12.3456",
                lon = "12.3456",
            ),
        )
    }

    @Test
    fun domainMapperFracTest2() {
        Assert.assertEquals(
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather =
                        listOf(
                            Weather(
                                id = 100,
                                description = "Sunny",
                                icon = "01d",
                            ),
                        ),
                    main =
                        Main(
                            temp = -12.234,
                            feelsLike = 98.999,
                            tempMin = -0.0001,
                            tempMax = 0.9999,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = "City 17",
                ),
            ),
            WeatherData(
                place = "City 17",
                description = "Sunny",
                iconUrl = "https://openweathermap.org/img/wn/01d@4x.png",
                temperature = "-12",
                tempMin = "0",
                tempMax = "0",
                feelsLike = "+98",
                lat = "12.3456",
                lon = "12.3456",
            ),
        )
    }
}
