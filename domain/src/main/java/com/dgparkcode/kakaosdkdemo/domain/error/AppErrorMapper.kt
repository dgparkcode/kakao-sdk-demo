package com.dgparkcode.kakaosdkdemo.domain.error

interface AppErrorMapper {

    fun mapFrom(t: Throwable): AppError
}