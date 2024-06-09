package com.lucidity.assessment.order.services

import com.lucidity.assessment.order.models.Order
import com.lucidity.assessment.order.repositories.InMemoryOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    private lateinit var orderRepository: InMemoryOrderRepository
    fun addOrder(order: Order) {

        orderRepository.addOrder(order)
    }

    fun getAllOrders(): List<Order> {

        return orderRepository.getAllOrders()
    }
}
