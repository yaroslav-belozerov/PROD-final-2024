package com.yaabelozerov.lifestylehub.weather.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yaabelozerov.lifestylehub.location.domain.LocationTracker
import com.yaabelozerov.lifestylehub.weather.domain.repository.WeatherRepository
import com.yaabelozerov.lifestylehub.weather.domain.util.Resource
import dagger.Module
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