package com.lucidity.assessment.common.models

data class Location (
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val type: LocationType
)

enum class LocationType {
    START, RESTAURANT, CONSUMER
}