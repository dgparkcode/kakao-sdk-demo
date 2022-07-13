package com.dgparkcode.kakaosdkdemo.domain.usecase

interface UseCase<DATA> {

    suspend operator fun invoke(): DATA
}