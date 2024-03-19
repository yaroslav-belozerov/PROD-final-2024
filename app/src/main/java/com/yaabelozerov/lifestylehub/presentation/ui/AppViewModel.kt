package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.lifestylehub.location.domain.LocationTracker
import com.yaabelozerov.lifestylehub.weather.domain.repository.WeatherRepository
import com.yaabelozerov.lifestylehub.weather.domain.util.Resource
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppViewModel() : ViewModel() {
}