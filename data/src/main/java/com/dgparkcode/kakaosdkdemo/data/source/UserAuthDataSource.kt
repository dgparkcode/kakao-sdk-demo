package com.dgparkcode.kakaosdkdemo.data.source

import android.content.Context
import com.dgparkcode.kakaosdkdemo.data.error.UnknownException
import com.dgparkcode.kakaosdkdemo.data.model.UserAuthData
import com.dgparkcode.kakaosdkdemo.data.model.mapToLoginData
import com.dgparkcode.kakaosdkdemo.data.utils.KakaoActivityContextStore
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface UserAuthDataSource {

    suspend fun login(): UserAuthData.LoginData

    suspend fun logout()
}

class UserAuthDataSourceImpl @Inject constructor() : UserAuthDataSource {

    override suspend fun login(): UserAuthData.LoginData {
        return suspendCancellableCoroutine { continuation ->
            val contextStore = KakaoActivityContextStore.getInstance()
            val context = contextStore.getActivityContext() ?: kotlin.run {
                val error = Exception("Activity context in KakaoActivityContext is required.")
                continuation.resumeWithException(error)
                return@suspendCancellableCoroutine
            }

            if (isKakaoTalkAvailable(context)) {
                loginKakaoTalk(context, continuation)
            } else {
                loginKakaoAccount(context, continuation)
            }
        }
    }

    private fun isKakaoTalkAvailable(context: Context) =
        UserApiClient.instance.isKakaoTalkLoginAvailable(context)

    private fun loginKakaoTalk(
        context: Context,
        continuation: CancellableContinuation<UserAuthData.LoginData>
    ) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            when {
                error != null -> {
                    if (isUserCanceled(error)) {
                        continuation.resume(UserAuthData.LoginData(isCanceled = true))
                    } else {
                        loginKakaoAccount(context, continuation)
                    }
                }
                token != null -> continuation.resume(token.mapToLoginData())
                else -> continuation.resumeWithException(UnknownException())
            }
        }
    }

    private fun isUserCanceled(error: Throwable?) =
        error is ClientError && error.reason == ClientErrorCause.Cancelled

    private fun loginKakaoAccount(
        context: Context,
        continuation: CancellableContinuation<UserAuthData.LoginData>
    ) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            when {
                error != null -> continuation.resumeWithException(error)
                token != null -> continuation.resume(token.mapToLoginData())
                else -> continuation.resumeWithException(UnknownException())
            }
        }
    }

    override suspend fun logout() {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(Unit)
                }
            }
        }
    }
}