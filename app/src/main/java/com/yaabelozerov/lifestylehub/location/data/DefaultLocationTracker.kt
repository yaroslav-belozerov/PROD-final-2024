package com.yaabelozerov.lifestylehub.location.data

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
import com.yaabelozerov.lifestylehub.location.domain.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient, private val application: Application
) : LocationTracker {
    var lastKnownLocation = mutableStateOf<Location?>(null)
    fun updateLocation(): Location? {
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
            LocationManager.NETWORK_PROVIDER, 10000L, 0F, locationListener
        )
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    override suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            lastKnownLocation.value = updateLocation()
            continuation.resume(lastKnownLocation.value)
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            lastKnownLocation.value = location
            Log.d("DefaultLocationTracker_lastKnown", lastKnownLocation.toString())
        }


        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}