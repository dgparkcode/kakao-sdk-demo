package com.dgparkcode.kakaosdkdemo.data.error

class UnknownException(
    override val message: String = "unknown"
) : RuntimeException(message)