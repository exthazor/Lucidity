package com.lucidity.assessment


import com.lucidity.assessment.common.models.Location
import com.lucidity.assessment.common.models.LocationType
import com.lucidity.assessment.order.models.Order
import com.lucidity.assessment.order.repositories.InMemoryOrderRepository
import com.lucidity.assessment.order.services.OrderService
import com.lucidity.assessment.route.services.RouteService
import com.lucidity.assessment.user.models.Consumer
import com.lucidity.assessment.user.models.DeliveryExecutive
import com.lucidity.assessment.user.models.Restaurant
import com.lucidity.assessment.route.strategies.RestaurantPrecedenceRouteStrategy
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LucidityApplication

fun main() {

    val inMemoryOrderRepository = InMemoryOrderRepository()
    val orderService = OrderService()
    val routeService = RouteService()

    // Sample Locations
    val restaurantA = Restaurant(Location("Restaurant A", 12.935242, 77.624474, LocationType.RESTAURANT), 10)  // Koramangala
    val restaurantB = Restaurant(Location("Restaurant B", 12.9611159, 77.6362214, LocationType.RESTAURANT), 15) // MG Road
    val consumerA = Consumer(Location("Consumer A", 12.9279232, 77.6271078, LocationType.CONSUMER))            // Jayanagar
    val consumerB = Consumer(Location("Consumer B", 12.9715987, 77.5945627, LocationType.CONSUMER))            // Indiranagar

    // Adding Orders
    orderService.addOrder(Order(restaurantA, consumerA))
    orderService.addOrder(Order(restaurantB, consumerB))

    // Delivery Executive's starting location (closer to Koramangala)
    val executiveStart = DeliveryExecutive(Location("Delivery Executive Start", 12.935242, 77.624474, LocationType.START))
    val strategy = RestaurantPrecedenceRouteStrategy()

    // Plan route
    val routePlan = routeService.planRoute(executiveStart, strategy)

    // Print the route plan
    routePlan.steps.forEach {
        println("${it.description} to ${it.location} taking ${it.durationMinutes} minutes.")
    }
    println("Total time for route: ${routePlan.totalTime} minutes")
}
