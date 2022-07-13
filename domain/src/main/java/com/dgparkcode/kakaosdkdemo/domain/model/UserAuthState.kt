package com.dgparkcode.kakaosdkdemo.domain.model

sealed class UserAuthState {

    sealed class LoginState {

        object LoggedIn : LoginState()

        object Canceled : LoginState()
    }

    sealed class LogoutState {

        object LoggedOut : LogoutState()
    }
}