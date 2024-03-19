package com.yaabelozerov.lifestylehub.weather.data.remote.mapper

import com.yaabelozerov.lifestylehub.weather.data.remote.OwmWeatherDTO
import com.yaabelozerov.lifestylehub.weather.data.remote.model.Weather
import com.yaabelozerov.lifestylehub.weather.domain.mapper.DomainMapper
import com.yaabelozerov.lifestylehub.weather.domain.model.WeatherData
import com.yaabelozerov.lifestylehub.weather.util.Constants

class OwmWeatherWeatherDomainMapper : DomainMapper<OwmWeatherDTO, WeatherData> {
    override fun mapToDomainModel(obj: OwmWeatherDTO): WeatherData {
        return WeatherData(
            place = obj.name ?: "",
            description = obj.weather?.get(0)?.description ?: "",
            iconUrl = Constants.OWM_ICON_URL + (obj.weather?.get(0)?.icon
                ?: Constants.OWM_ICON_PLACEHOLDER) + Constants.OWM_ICON_PARAMETERS,
            temperature = obj.main!!.temp!!.toInt(),
            feelsLike = obj.main!!.feelsLike!!.toInt(),
            lat = obj.coord!!.lat.toString(),
            lon = obj.coord!!.lon.toString()
        )
    }
}