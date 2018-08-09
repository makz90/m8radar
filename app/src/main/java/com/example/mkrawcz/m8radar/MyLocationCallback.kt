package com.example.mkrawcz.m8radar

import android.hardware.GeomagneticField
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class MyLocationCallback(private val activity: MapsActivity) : LocationCallback() {

    private lateinit var geoField: GeomagneticField

    override fun onLocationResult(p0: LocationResult?) {
        val newLocation = p0?.lastLocation
        val position = newLocation?.toLatLng() ?: return

        activity.animateToPosition(position)

        geoField = GeomagneticField(
                java.lang.Double.valueOf(newLocation.latitude).toFloat(),
                java.lang.Double.valueOf(newLocation.longitude).toFloat(),
                java.lang.Double.valueOf(newLocation.altitude).toFloat(),
                System.currentTimeMillis()
        )

//        var heading = geoField.declination
//        val myBearing = activity.selfLocation.bearingTo(newLocation)
//        heading = (myBearing - heading) * -1
//
//        val angle = normalizeDegree(heading)
//
//        activity.rotateSelfMarker(angle)
//        activity.selfLocation = newLocation
    }

    private fun normalizeDegree(value: Float): Float{
        if(value in 0.0f..180.0f){
            return value
        }else{
            return 180 + (180 + value)
        }
    }
}