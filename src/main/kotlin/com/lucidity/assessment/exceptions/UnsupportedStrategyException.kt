package com.lucidity.assessment.exceptions

import com.lucidity.assessment.utils.ApiMessages

class UnsupportedStrategyException(
    message: String = ApiMessages.ExceptionMessages.UNSUPPORTED_STRATEGY_MESSAGE,
    errorCode: Int = 1001
) : CustomException(message, errorCode = errorCode)