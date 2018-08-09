package com.example.mkrawcz.m8radar

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class LocationUpdateCallback(private val activity: MapsActivity) : LocationCallback() {

    override fun onLocationResult(p0: LocationResult?) {
        val newLocation = p0?.lastLocation ?: return

        if (activity.selfMarker == null){
            activity.initializeSelfMarker(newLocation)
        }

        val position = newLocation.toLatLng()
        activity.animateToPosition(position)
        activity.selfMarker?.position = position
    }
}