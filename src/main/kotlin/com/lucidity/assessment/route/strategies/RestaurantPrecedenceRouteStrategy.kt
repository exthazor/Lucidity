package com.lucidity.assessment.route.strategies

import com.lucidity.assessment.user.models.DeliveryExecutive
import com.lucidity.assessment.order.models.Order
import com.lucidity.assessment.route.models.RoutePlan
import com.lucidity.assessment.route.models.RouteStep
import java.util.PriorityQueue

class RestaurantPrecedenceRouteStrategy : RouteStrategy {
    override fun findOptimalRoute(executive: DeliveryExecutive, orders: List<Order>): RoutePlan {

        val routeSteps = mutableListOf<RouteStep>()
        var totalTime = 0.0  // Initialize total time accumulator
        val pq = PriorityQueue<Order> { o1, o2 ->
            val time1 = maxOf(
                executive.travelTimeTo(o1.restaurant.location),
                o1.restaurant.readyTime().toDouble()
            ) + executive.travelTimeTo(o1.consumer.location)
            val time2 = maxOf(
                executive.travelTimeTo(o2.restaurant.location),
                o2.restaurant.readyTime().toDouble()
            ) + executive.travelTimeTo(o2.consumer.location)
            time1.compareTo(time2)
        }

        pq.addAll(orders)

        while (pq.isNotEmpty()) {
            val order = pq.poll()
            val toRestaurantTime = executive.travelTimeTo(order.restaurant.location)
            val waitTime = maxOf(0.0, order.restaurant.readyTime() - toRestaurantTime)
            executive.updateLocation(order.restaurant.location)  // Update location to restaurant
            routeSteps.add(RouteStep("Travel to ${order.restaurant.location.name}", toRestaurantTime, waitTime))
            totalTime += toRestaurantTime + waitTime  // Accumulate travel and wait times

            val toConsumerTime = executive.travelTimeTo(order.consumer.location)
            executive.updateLocation(order.consumer.location)  // Update location to consumer
            routeSteps.add(RouteStep("Deliver to ${order.consumer.location.name}", toConsumerTime, 0.0))
            totalTime += toConsumerTime  // Accumulate travel time
        }

        return RoutePlan(routeSteps, totalTime)
    }
}
