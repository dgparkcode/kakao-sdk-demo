package com.dgparkcode.kakaosdkdemo.domain.error

sealed class AppError {

    object NetworkError : AppError()

    object NotFoundError : AppError()

    object AccessDeniedError : AppError()

    object ServiceUnavailable : AppError()

    object UnknownError : AppError()
}
