package com.lucidity.assessment.user.models

import com.lucidity.assessment.common.models.Location

class Restaurant(override val location: Location, private val preparationTime: Long) : Party {
    override fun readyTime() = preparationTime
}
