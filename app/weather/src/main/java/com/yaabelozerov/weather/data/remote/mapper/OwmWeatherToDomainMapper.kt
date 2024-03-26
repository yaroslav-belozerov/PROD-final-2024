package com.yaabelozerov.weather.data.remote.mapper

import com.yaabelozerov.common.domain.DomainMapper
import com.yaabelozerov.weather.data.remote.OwmWeatherDTO
import com.yaabelozerov.weather.data.remote.util.Constants
import com.yaabelozerov.weather.domain.model.WeatherData

class OwmWeatherToDomainMapper : DomainMapper<OwmWeatherDTO, WeatherData> {
    private fun plusToDouble(x: Double): String {
        return "${if (x.toInt() > 0) "+" else ""}${x.toInt()}"
    }

    override fun mapToDomainModel(obj: OwmWeatherDTO): WeatherData {
        return WeatherData(
            place = obj.name ?: "",
            description = obj.weather?.get(0)?.description?.replaceFirstChar(Char::titlecase) ?: "",
            // Constructing an icon url from icon directory, value and parameters
            iconUrl =
                Constants.OWM_ICON_URL + (
                    obj.weather?.get(0)?.icon
                        ?: Constants.OWM_ICON_PLACEHOLDER
                ) + Constants.OWM_ICON_PARAMETERS,
            // These add a + to positive temperatures
            temperature = plusToDouble(obj.main!!.temp!!),
            tempMin = plusToDouble(obj.main!!.tempMin!!),
            tempMax = plusToDouble(obj.main!!.tempMax!!),
            feelsLike = plusToDouble(obj.main!!.feelsLike!!),
            lat = obj.coord!!.lat!!.toString(),
            lon = obj.coord!!.lon!!.toString(),
        )
    }
}
