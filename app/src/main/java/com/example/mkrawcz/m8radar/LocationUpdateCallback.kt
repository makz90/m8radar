package com.example.mkrawcz.m8radar

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class LocationUpdateCallback(private val activity: MapsActivity) : LocationCallback() {

    override fun onLocationResult(p0: LocationResult?) {
        val newLocation = p0?.lastLocation
        val position = newLocation?.toLatLng() ?: return

        activity.animateToPosition(position)
        activity.selfMarker?.position = position
    }
}