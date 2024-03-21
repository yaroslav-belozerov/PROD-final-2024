package com.yaabelozerov.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.weather.domain.repository.WeatherRepository
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherCardViewModel @Inject constructor(
    private val repository: WeatherRepository, private val locationTracker: LocationTracker
) : ViewModel() {
    private var _weatherMutableStateFlow = MutableStateFlow(WeatherState())
    var weatherMutableStateFlow = _weatherMutableStateFlow.asStateFlow()
    fun loadWeatherInfo() {
        viewModelScope.launch {
            _weatherMutableStateFlow.update { weatherState ->
                weatherState.copy(isLoading = true, error = null)
            }
            try {
                locationTracker.getCurrentLocation()?.let { location ->
                    when (val result = repository.getWeatherData(
                        lat = location.latitude,
                        lon = location.longitude,
                        Locale.getDefault().language
                    )) {
                        is Resource.Success -> _weatherMutableStateFlow.update { weatherState ->
                            weatherState.copy(
                                weatherData = result.data, isLoading = false, error = null
                            )
                        }

                        is Resource.Error -> _weatherMutableStateFlow.update { weatherState ->
                            weatherState.copy(
                                weatherData = null, isLoading = false, error = result.message
                            )
                        }

                    }

                } ?: kotlin.run {
                    _weatherMutableStateFlow.update { weatherState ->
                        weatherState.copy(
                            isLoading = false,
                            error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}