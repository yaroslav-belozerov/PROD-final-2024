package com.yaabelozerov.weather.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.weather.domain.repository.WeatherRepository
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WeatherCardViewModel @Inject constructor(
    private val repository: WeatherRepository, private val locationTracker: LocationTracker
) : ViewModel() {
    private val _weather = mutableStateOf(WeatherState())
    val weather: State<WeatherState> = _weather
    fun loadWeatherInfo() {
        viewModelScope.launch {
//            _weather.value = weather.value.copy(weatherData = null, isLoading = true, error = null)
            try {
                locationTracker.getCurrentLocation()?.let { location ->
                    when (val result = repository.getWeatherData(
                        lat = location.latitude,
                        lon = location.longitude,
                        Locale.getDefault().language
                    )) {
                        is Resource.Success -> _weather.value = weather.value.copy(
                            weatherData = result.data, isLoading = false, error = null
                        )

                        is Resource.Error -> _weather.value = weather.value.copy(
                            weatherData = null, isLoading = false, error = result.message
                        )
                    }
                } ?: kotlin.run {
                    _weather.value = _weather.value.copy(
                        weatherData = null,
                        isLoading = false,
                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}