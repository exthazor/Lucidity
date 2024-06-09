package com.lucidity.assessment.order.repositories

import com.lucidity.assessment.order.models.Order
import org.springframework.stereotype.Repository

@Repository
class InMemoryOrderRepository : OrderRepository {

    private val orders = mutableListOf<Order>()

    override fun addOrder(order: Order) {

        orders.add(order)
    }

    override fun getAllOrders(): List<Order> {

        return orders.toList()
    }
}
