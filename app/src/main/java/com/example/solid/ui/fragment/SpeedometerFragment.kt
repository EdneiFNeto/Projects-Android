package com.example.solid.ui.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.solid.databinding.FragmentVelocimetroBinding
import com.example.solid.model.Speedometer
import kotlin.random.Random

private const val TAG = "SpeedometerFragment"

class SpeedometerFragment : BasaFragment(), LocationListener {
    private lateinit var velocimetroBinding: FragmentVelocimetroBinding
    private var speedometer: Speedometer = Speedometer(km = 0.0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        velocimetroBinding = FragmentVelocimetroBinding.inflate(inflater, container, false)
        return velocimetroBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        velocimetroBinding.speedometer = speedometer
        requestPermission()
        locationPermission()
    }

    private fun locationPermission() {
        var locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        context?.let {
            if (ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, this)
        }
    }

    override fun onLocationChanged(location: Location) {

        var speed = (location.speed * 3600) / 1000
        val veloc = getSpeed(speed).toFloat()

        if (location.hasSpeed()) {
            addKM(veloc.toDouble())
        } else {
            addKM(0.0)
        }

        Log.e(TAG, "Speed $veloc")
    }

    private fun addKM(veloc: Double) {
        speedometer.km = veloc
        velocimetroBinding.speedometer = speedometer
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
    }

    private fun getSpeed(speed: Float) = when {
        speed > 0.0 -> speed.toDouble()
        speed > 5.0 -> speed.toDouble()
        speed > 10.0 -> speed.toDouble()
        else -> 0.0
    }

    private fun requestPermission() {
        var permissionsToRequest = mutableListOf<String>()
        context?.let {
            if (!getPermission(it)) {
                permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
                permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            if (permissionsToRequest.isNotEmpty()) {
                activity?.let { activity ->
                    ActivityCompat.requestPermissions(
                        activity,
                        permissionsToRequest.toTypedArray(),
                        0
                    )
                }
            }
        }
    }

    private fun getPermission(context: Context) =
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) ==
                PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Permission", "${permissions[i]} granted")
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

}