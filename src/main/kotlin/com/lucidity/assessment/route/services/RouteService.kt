package com.lucidity.assessment.route.services

import com.lucidity.assessment.order.services.OrderService
import com.lucidity.assessment.exceptions.UnsupportedStrategyException
import com.lucidity.assessment.user.models.DeliveryExecutive
import com.lucidity.assessment.route.models.RoutePlan
import com.lucidity.assessment.route.strategies.RestaurantPrecedenceRouteStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RouteService {

    @Autowired
    private lateinit var orderService: OrderService
    companion object {
        const val MAX_ORDERS = 10
    }

    fun planRoute(executive: DeliveryExecutive, strategy: RestaurantPrecedenceRouteStrategy): RoutePlan {

        return try {
            val orders = orderService.getAllOrders()
            if (orders.size > MAX_ORDERS) {
                throw UnsupportedStrategyException("Exceeds the maximum number of orders: $MAX_ORDERS")
            }

            strategy.findOptimalRoute(executive, orders)

        } catch (e: Exception) {
            println(e.message)
            RoutePlan(emptyList(), 0.0)
        }
    }
}
