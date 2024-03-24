package com.yaabelozerov.venues.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesCardViewModel
    @Inject
    constructor(
        private val repository: VenuesRepository,
        private val locationTracker: LocationTracker,
    ) : ViewModel() {
        private var _venues = MutableStateFlow(VenuesState())
        val venues = _venues.asStateFlow()

        fun loadVenues() {
            Log.i("Function Call", "loadVenues")
            viewModelScope.launch {
                try {
                    locationTracker.getCurrentLocation()?.let { location ->
                        repository.getVenues(
                            lat = location.latitude, lon = location.longitude, radius = 1000,
                        ).collect { result ->
                            when (result) {
                                is Resource.Error -> {
                                    Log.e("Error getting venues", "${result.message}")
                                    _venues.emit(VenuesState(venues = emptyList(), error = result.message, isLoading = false))
                                }

                                is Resource.Success -> {
                                    Log.i("Got venues!", "${result.data}")
                                    _venues.emit(VenuesState(venues = result.data!!, error = null, isLoading = false))
                                }
                            }
                        }
                    } ?: kotlin.run {
                        Log.e("Error getting location", "location is null")
                        _venues.emit(VenuesState(venues = emptyList(), error = "location is null", isLoading = false))
                    }
                } catch (e: Exception) {
                    _venues.emit(VenuesState(venues = emptyList(), error = e.message ?: "Unknown error", isLoading = false))
                    e.printStackTrace()
                }
            }
        }
    }
