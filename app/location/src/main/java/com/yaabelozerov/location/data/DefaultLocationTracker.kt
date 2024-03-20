package com.yaabelozerov.location.data

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.yaabelozerov.location.domain.LocationTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient, private val application: Application
) : LocationTracker {
    private val _lastKnownLocation = MutableStateFlow<Location?>(null)
    private fun updateLocation(): Location? {
        val hasAccessCoarsePermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessFinePermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            ))

        if (!hasAccessCoarsePermission || !hasAccessFinePermission || !isGpsEnabled) {
            Log.w(
                "DefaultLocationTracker",
                "This module needs to have access to location and GPS: $hasAccessCoarsePermission $hasAccessFinePermission $isGpsEnabled"
            )
            return null
        }
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER, 10000L, 10F, locationListener
        )
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    override suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            _lastKnownLocation.value = updateLocation()
            continuation.resume(_lastKnownLocation.value)
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            _lastKnownLocation.value = location
            Log.d("DefaultLocationTracker_lastKnown", _lastKnownLocation.toString())
        }


        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}