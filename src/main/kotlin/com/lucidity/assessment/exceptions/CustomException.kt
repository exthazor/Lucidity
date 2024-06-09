package com.lucidity.assessment.exceptions

open class CustomException(
    message: String,
    cause: Throwable? = null,
    val errorCode: Int? = null
) : Exception(message, cause)