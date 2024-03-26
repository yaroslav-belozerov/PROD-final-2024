package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.lifecycle.ViewModel
import com.yaabelozerov.venues.domain.model.VenueData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsScreenViewModel : ViewModel() {
    private val _details = MutableStateFlow<VenueData?>(null)
    val details = _details.asStateFlow()

    fun loadDetails(data: VenueData) {
        _details.update { data }
    }
}
