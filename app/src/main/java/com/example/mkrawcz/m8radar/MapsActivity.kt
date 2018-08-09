package com.example.mkrawcz.m8radar

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.example.mkrawcz.m8radar.mocks.MockRadarClient
import com.example.mkrawcz.m8radar.network.client.RadarClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, SensorEventListener {

    private lateinit var gMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mSensorManager: SensorManager
    lateinit var selfLocation: Location

    private var selfMarker: Marker? = null
    private val radarClient: RadarClient

    companion object {
        const val REQUEST_LOCATION_ID = 1234
        const val REQUEST_CHECK_SETTINGS = 12345
        const val MinZoom = 10f
        const val DefaultZoom = 18f
    }

    init {
        radarClient = MockRadarClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = Math.round(event!!.values[0]).toFloat()
        selfMarker?.rotation = degree
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        setupMap()
        setupLocation()
    }

    private fun setupLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_ID)
        }

        val myLocationCallback = MyLocationCallback(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location -> initializeSelfMarker(location) }
        fusedLocationClient.requestLocationUpdates(createLocationRequest(), myLocationCallback, mainLooper)
    }

    private fun initializeSelfMarker(location: Location) {
        val position = location.toLatLng()
        createSelfMarker(position)
        animateToPosition(position, DefaultZoom)
        selfLocation = location
    }

    private fun createSelfMarker(position: LatLng) {
        val icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_map_mate)
        selfMarker = gMap.addMarker(MarkerOptions()
                .position(position)
                .title("self marker")
                .anchor(0.5f, 0.5f)
                .flat(true)
                .icon(icon))
    }

    fun animateToPosition(position: LatLng, zoom: Float? = null) {
        when (zoom) {
            null -> gMap.animateCamera(CameraUpdateFactory.newLatLng(position))
            else -> gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, zoom))
        }
    }

    private fun setupMap() {

        val mapStyleString = resources.openRawResource(R.raw.nolabels_map_style).bufferedReader().use { it.readText() }

        gMap.setMapStyle(MapStyleOptions(mapStyleString))
        gMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        gMap.isIndoorEnabled = true
        gMap.setMinZoomPreference(MinZoom)
        gMap.uiSettings.setAllGesturesEnabled(false)
        gMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun createLocationRequest(): LocationRequest{
        val locationRequest = LocationRequest().apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException){
                try {
                    exception.startResolutionForResult(this@MapsActivity, REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }

        return locationRequest
    }
}

fun Location.toLatLng(): LatLng {
    return LatLng(this.latitude, this.longitude)
}
