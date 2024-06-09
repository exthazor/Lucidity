package com.lucidity.assessment.user.models

import com.lucidity.assessment.common.models.Location
import com.lucidity.assessment.utils.GeoUtils

class DeliveryExecutive(private var currentLocation: Location) {
    fun travelTimeTo(destination: Location): Double {

        return GeoUtils.haversine(currentLocation, destination) / 20.0 * 60
    }

    fun getCurrentLocation(): Location {

        return currentLocation
    }

    fun updateLocation(newLocation: Location) {

        currentLocation = newLocation
    }
}