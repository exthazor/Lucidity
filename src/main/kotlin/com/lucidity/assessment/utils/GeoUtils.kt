package com.lucidity.assessment.utils

import com.lucidity.assessment.common.models.Location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object GeoUtils {
    fun haversine(from: Location, to: Location): Double {

        val earthRadius = 6371.0 // Radius of the earth in km
        val latDistance = Math.toRadians(to.latitude - from.latitude)
        val lonDistance = Math.toRadians(to.longitude - from.longitude)
        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                cos(Math.toRadians(from.latitude)) * cos(Math.toRadians(to.latitude)) *
                sin(lonDistance / 2) * sin(lonDistance / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }
}