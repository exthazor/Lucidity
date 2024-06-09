package com.lucidity.assessment.order.repositories

import com.lucidity.assessment.order.models.Order

interface OrderRepository {
    fun addOrder(order: Order)
    fun getAllOrders(): List<Order>
}