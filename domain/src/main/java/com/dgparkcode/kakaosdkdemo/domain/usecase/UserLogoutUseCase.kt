package com.dgparkcode.kakaosdkdemo.domain.usecase

import com.dgparkcode.kakaosdkdemo.domain.repository.UserAuthRepository
import com.dgparkcode.kakaosdkdemo.domain.utils.Result
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ResultUseCase<Unit> {

    override suspend fun invoke(): Result<Unit> {
        return userAuthRepository.logout()
    }
}