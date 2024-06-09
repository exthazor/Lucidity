package com.lucidity.assessment.route.models

data class RoutePlan(
    val steps: List<RouteStep>,
    val totalTime: Double
)