package com.dgparkcode.kakaosdkdemo.data.repository

import android.util.Log
import com.dgparkcode.kakaosdkdemo.data.source.UserAuthDataSource
import com.dgparkcode.kakaosdkdemo.domain.error.AppErrorMapper
import com.dgparkcode.kakaosdkdemo.domain.model.UserAuthState
import com.dgparkcode.kakaosdkdemo.domain.repository.UserAuthRepository
import com.dgparkcode.kakaosdkdemo.domain.utils.Result
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val userAuthDataSource: UserAuthDataSource,
    private val errorMapper: AppErrorMapper
) : UserAuthRepository {

    override suspend fun login(): Result<UserAuthState.LoginState> {
        return kotlin.runCatching {
            val data = userAuthDataSource.login()
            if (data.isCanceled) {
                Result.Success(UserAuthState.LoginState.Canceled)
            } else {
                Result.Success(UserAuthState.LoginState.LoggedIn)
            }
        }.getOrElse {
            Log.e(TAG, "login: error", it)
            Result.Failure(errorMapper.mapFrom(it))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return kotlin.runCatching {
            Result.Success(userAuthDataSource.logout())
        }.getOrElse {
            Log.e(TAG, "logout: error", it)
            Result.Failure(errorMapper.mapFrom(it))
        }
    }

    companion object {
        private const val TAG = "UserAuthRepository"
    }
}