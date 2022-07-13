package com.dgparkcode.kakaosdkdemo.domain.repository

import com.dgparkcode.kakaosdkdemo.domain.model.UserAuthState
import com.dgparkcode.kakaosdkdemo.domain.utils.Result

interface UserAuthRepository {

    suspend fun login(): Result<UserAuthState.LoginState>

    suspend fun logout(): Result<UserAuthState.LogoutState>
}