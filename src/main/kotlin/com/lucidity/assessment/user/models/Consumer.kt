package com.lucidity.assessment.user.models

import com.lucidity.assessment.common.models.Location

class Consumer(override val location: Location) : Party {
    override fun readyTime() = 0L
}