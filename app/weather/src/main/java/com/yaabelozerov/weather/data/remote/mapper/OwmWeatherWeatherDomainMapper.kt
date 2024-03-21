package com.yaabelozerov.weather.data.remote.mapper

import com.yaabelozerov.weather.data.remote.OwmWeatherDTO
import com.yaabelozerov.weather.domain.mapper.DomainMapper
import com.yaabelozerov.weather.domain.model.WeatherData
import com.yaabelozerov.weather.data.remote.util.Constants

class OwmWeatherWeatherDomainMapper : DomainMapper<OwmWeatherDTO, WeatherData> {
    override fun mapToDomainModel(obj: OwmWeatherDTO): WeatherData {
        return WeatherData(
            place = obj.name ?: "Failed to get name",
            description = obj.weather?.get(0)?.description?.replaceFirstChar(Char::titlecase) ?: "Failed to get description",
            iconUrl = Constants.OWM_ICON_URL + (obj.weather?.get(0)?.icon
                ?: Constants.OWM_ICON_PLACEHOLDER) + Constants.OWM_ICON_PARAMETERS, // Constructing an icon url from icon directory, value and parameters
            // These add a + to positive temperatures
            temperature = "${if (obj.main!!.temp!!.toInt() > 0) "+" else ""}${obj.main!!.temp!!.toInt()}°",
            feelsLike = "${if (obj.main!!.feelsLike!!.toInt() > 0) "+" else ""}${obj.main!!.feelsLike!!.toInt()}°",
            lat = obj.coord!!.lat.toString(),
            lon = obj.coord!!.lon.toString()
        )
    }
}