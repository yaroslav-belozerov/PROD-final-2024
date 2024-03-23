package com.yaabelozerov.venues.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesCardViewModel
    @Inject
    constructor(
        private val repository: VenuesRepository,
        private val locationTracker: LocationTracker,
    ) : ViewModel() {
        private val _venues = mutableStateOf(VenuesState())
        val venues: State<VenuesState> = _venues

        fun loadVenues() {
            viewModelScope.launch {
//            _venues.value = _venues.value.copy(isLoading = true, error = null)
                try {
                    locationTracker.getCurrentLocation()?.let { location ->
                        when (
                            val result =
                                repository.getVenues(
                                    lat = location.latitude, lon = location.longitude, radius = 1000,
                                )
                        ) {
                            is Resource.Success ->
                                _venues.value =
                                    _venues.value.copy(venues = result.data!!, isLoading = false, error = null)

                            is Resource.Error ->
                                _venues.value =
                                    _venues.value.copy(isLoading = false, error = result.message)
                        }
                    } ?: kotlin.run {
                        _venues.value =
                            _venues.value.copy(
                                isLoading = false,
                                error = "Couldn't retrieve location. Make sure to grant permission and enable GPS.",
                            )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
