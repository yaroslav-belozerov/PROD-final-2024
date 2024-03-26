package com.yaabelozerov.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.common.presentation.CommonConstants
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.weather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherCardViewModel
    @Inject
    constructor(
        private val repository: WeatherRepository,
        private val locationTracker: LocationTracker,
    ) : ViewModel() {
        private val _weather = MutableStateFlow(WeatherState())
        val weather = _weather.asStateFlow()

        fun loadWeatherInfo() {
            viewModelScope.launch {
                try {
                    locationTracker.getCurrentLocation()?.let { location ->
                        when (
                            val result =
                                repository.getWeatherData(
                                    lat = location.latitude,
                                    lon = location.longitude,
                                    Locale.getDefault().language,
                                )
                        ) {
                            is Resource.Success ->
                                _weather.update {
                                    WeatherState(
                                        weatherData = result.data,
                                        isLoading = false,
                                        error = null,
                                    )
                                }

                            is Resource.Error ->
                                _weather.update {
                                    WeatherState(
                                        weatherData = null,
                                        isLoading = false,
                                        error = result.message,
                                    )
                                }
                        }
                    } ?: kotlin.run {
                        _weather.update {
                            WeatherState(
                                weatherData = null,
                                isLoading = false,
                                error = CommonConstants.ErrorMessages.LOCATION,
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _weather.update {
                        WeatherState(
                            weatherData = null,
                            isLoading = false,
                            error = e.message,
                        )
                    }
                }
            }
        }
    }
