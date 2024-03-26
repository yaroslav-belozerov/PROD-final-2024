package com.yaabelozerov.venues.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaabelozerov.common.domain.Resource
import com.yaabelozerov.common.presentation.CommonConstants
import com.yaabelozerov.location.domain.LocationTracker
import com.yaabelozerov.venues.data.util.Constants
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
        var venues = _venues.asStateFlow()

        fun loadVenues() {
            viewModelScope.launch {
                try {
                    locationTracker.getCurrentLocation()?.let { location ->
                        repository.getVenues(
                            lat = location.latitude, lon = location.longitude, radius = Constants.FSQ_RADIUS,
                        ).collect { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _venues.emit(VenuesState(venues = emptyList(), error = result.message, isLoading = false))
                                }

                                is Resource.Success -> {
                                    _venues.emit(VenuesState(venues = result.data!!, error = null, isLoading = false))
                                }
                            }
                        }
                    } ?: kotlin.run {
                        _venues.emit(VenuesState(venues = emptyList(), error = CommonConstants.ErrorMessages.LOCATION, isLoading = false))
                    }
                } catch (e: Exception) {
                    _venues.emit(
                        VenuesState(venues = emptyList(), error = e.message ?: CommonConstants.ErrorMessages.LOCATION, isLoading = false),
                    )
                    e.printStackTrace()
                }
            }
        }
    }
