package com.dgparkcode.kakaosdkdemo.domain.usecase

import com.dgparkcode.kakaosdkdemo.domain.model.UserAuthState
import com.dgparkcode.kakaosdkdemo.domain.repository.UserAuthRepository
import com.dgparkcode.kakaosdkdemo.domain.utils.Result
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ResultUseCase<UserAuthState.LoginState> {

    override suspend fun invoke(): Result<UserAuthState.LoginState> {
        return userAuthRepository.login()
    }
}