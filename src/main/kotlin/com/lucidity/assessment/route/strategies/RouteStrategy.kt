package com.lucidity.assessment.route.strategies

import com.lucidity.assessment.user.models.DeliveryExecutive
import com.lucidity.assessment.order.models.Order
import com.lucidity.assessment.route.models.RoutePlan

interface RouteStrategy {
    fun findOptimalRoute(executive: DeliveryExecutive, orders: List<Order>): RoutePlan
}