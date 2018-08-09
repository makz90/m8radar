package com.example.mkrawcz.m8radar

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

class LocationUpdateCallback(private val googleMap: GoogleMap) : LocationCallback() {

    override fun onLocationResult(p0: LocationResult?) {
        val newLocation = p0?.lastLocation ?: return

//        if (activity.selfMarker == null){
//            activity.initializeSelfMarker(newLocation)
//        }

        val position = newLocation.toLatLng()
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(position))
//        activity.selfMarker?.position = position
    }
}