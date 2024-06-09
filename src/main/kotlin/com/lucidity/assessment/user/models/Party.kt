package com.lucidity.assessment.user.models

import com.lucidity.assessment.common.models.Location

interface Party {
    val location: Location
    fun readyTime(): Long
}