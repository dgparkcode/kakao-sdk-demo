package com.dgparkcode.kakaosdkdemo.data.model

import com.kakao.sdk.auth.model.OAuthToken

sealed class UserAuthData {

    data class LoginData(
        val accessToken: String = "",
        val isCanceled: Boolean = false
    )
}

fun OAuthToken.mapToLoginData(): UserAuthData.LoginData {
    return UserAuthData.LoginData(
        accessToken = accessToken,
        isCanceled = false
    )
}