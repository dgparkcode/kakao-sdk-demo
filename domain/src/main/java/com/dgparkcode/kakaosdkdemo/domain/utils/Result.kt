package com.dgparkcode.kakaosdkdemo.domain.utils

import com.dgparkcode.kakaosdkdemo.domain.error.AppError

sealed class Result<out DATA> {

    data class Success<DATA>(val data: DATA) : Result<DATA>()

    data class Failure(val error: AppError) : Result<Nothing>()
}

inline fun <T> Result<T>.onSucceeded(block: (T) -> Unit): Result<T> {
    if (this is Result.Success) block(data)
    return this
}

inline fun <T> Result<T>.onFailed(block: (AppError) -> Unit): Result<T> {
    if (this is Result.Failure) block(error)
    return this
}