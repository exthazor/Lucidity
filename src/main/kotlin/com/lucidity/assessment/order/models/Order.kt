package com.lucidity.assessment.order.models

import com.lucidity.assessment.user.models.Consumer
import com.lucidity.assessment.user.models.Restaurant

data class Order(
    val restaurant: Restaurant,
    val consumer: Consumer
)