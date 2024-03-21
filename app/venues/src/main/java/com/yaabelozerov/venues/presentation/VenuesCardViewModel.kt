package com.yaabelozerov.venues.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.venues.domain.repository.VenuesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesCardViewModel @Inject constructor(
    private val repository: VenuesRepository, private val locationTracker: LocationTracker
) : ViewModel() {
    private var _venuesMutableStateFlow = MutableStateFlow(VenuesState())
    var venuesMutableStateFlow = _venuesMutableStateFlow.asStateFlow()

    fun loadVenues() {
        viewModelScope.launch {
            _venuesMutableStateFlow.update { venuesState ->
                venuesState.copy(isLoading = true, error = null)
            }
            try {
                locationTracker.getCurrentLocation()?.let { location ->
                    when (val result = repository.getVenues(
                        lat = location.latitude, lon = location.longitude, radius = 1000
                    )) {
                        is Resource.Success -> _venuesMutableStateFlow.update { venuesState ->
                            venuesState.copy(
                                venues = result.data!!, isLoading = false, error = null
                            )
                        }
                        is Resource.Error -> _venuesMutableStateFlow.update { venuesState ->
                            venuesState.copy(
                                venues = emptyList(), isLoading = false, error = result.message
                            )
                        }
                    }
                } ?: kotlin.run {
                    _venuesMutableStateFlow.update { venuesState ->
                        venuesState.copy(
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
