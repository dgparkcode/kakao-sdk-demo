package com.dgparkcode.kakaosdkdemo.data.utils

import com.dgparkcode.kakaosdkdemo.domain.error.AppError
import com.dgparkcode.kakaosdkdemo.domain.error.AppErrorMapper
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class AppErrorMapperImpl : AppErrorMapper {

    override fun mapFrom(t: Throwable): AppError {
        return when (t) {
            is IOException -> AppError.NetworkError
            is HttpException -> {
                when (t.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> AppError.NotFoundError
                    HttpURLConnection.HTTP_FORBIDDEN -> AppError.AccessDeniedError
                    HttpURLConnection.HTTP_UNAVAILABLE -> AppError.ServiceUnavailable
                    else -> AppError.UnknownError
                }
            }
            else -> AppError.UnknownError
        }
    }
}