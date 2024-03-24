package com.yaabelozerov.venues.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesCardViewModel
    @Inject
    constructor(
        private val repository: VenuesRepository,
        private val locationTracker: LocationTracker,
    ) : ViewModel() {
        val state = mutableStateOf(VenuesState(isLoading = true, error = null, venues = emptyList()))

        fun loadVenues() {
            viewModelScope.launch {
//            _venues.value = _venues.value.copy(isLoading = true, error = null)
                try {
                    locationTracker.getCurrentLocation()?.let { location ->
                        repository.getVenues(
                            lat = location.latitude, lon = location.longitude, radius = 1000,
                        ).collectLatest { result ->
                            when (result) {
                                is Resource.Error -> {
                                    Log.e("Error getting venues", "${result.message}")
                                    state.value = state.value.copy(venues = emptyList(), error = result.message, isLoading = false)
                                }

                                is Resource.Success -> {
                                    state.value = state.value.copy(venues = result.data!!, error = null, isLoading = false)
                                }
                            }
                        }
                    } ?: kotlin.run {
                        Log.e("Error getting location", "location is null")
                        state.value = state.value.copy(venues = emptyList(), error = "location is null", isLoading = false)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
