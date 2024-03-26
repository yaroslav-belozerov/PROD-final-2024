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
        )
    }

    @Test
    fun domainMapperIntegerTest2() {
        Assert.assertEquals(
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
        )
    }

    @Test
    fun domainMapperIntegerTest3() {
        Assert.assertEquals(
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
        )
    }

    @Test
    fun domainMapperFracTest1() {
        Assert.assertEquals(
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
        )
    }

    @Test
    fun domainMapperFracTest2() {
        Assert.assertEquals(
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
        )
    }

    @Test
    fun domainMapperNullNoExceptionTest() {
        Assert.assertEquals(
            WeatherData(
                place = "",
                description = "",
                iconUrl = "https://openweathermap.org/img/wn/10d@4x.png",
                temperature = "-12",
                tempMin = "0",
                tempMax = "0",
                feelsLike = "+98",
                lat = "12.3456",
                lon = "12.3456",
            ),
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = 12.3456),
                    weather = null,
                    main =
                        Main(
                            temp = -12.234,
                            feelsLike = 98.999,
                            tempMin = -0.0001,
                            tempMax = 0.9999,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = null,
                ),
            ),
        )
    }

    @Test
    fun domainMapperNullExceptionTest1() {
        Assert.assertThrows(NullPointerException::class.java) {
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = null,
                    weather = null,
                    main =
                        Main(
                            temp = -12.234,
                            feelsLike = 98.999,
                            tempMin = -0.0001,
                            tempMax = 0.9999,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = null,
                ),
            )
        }
    }

    @Test
    fun domainMapperNullExceptionTest2() {
        Assert.assertThrows(NullPointerException::class.java) {
            mapper.mapToDomainModel(
                OwmWeatherDTO(
                    coord = Coord(lat = 12.3456, lon = null),
                    weather = listOf(Weather(id = null, description = null, icon = null)),
                    main =
                        Main(
                            temp = -12.234,
                            feelsLike = 98.999,
                            tempMin = -0.0001,
                            tempMax = 0.9999,
                            pressure = 1234,
                            humidity = 5678,
                        ),
                    name = null,
                ),
            )
        }
    }
}
