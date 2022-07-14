package com.dgparkcode.kakaosdkdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgparkcode.kakaosdkdemo.domain.model.UserAuthState
import com.dgparkcode.kakaosdkdemo.domain.usecase.UserLoginUseCase
import com.dgparkcode.kakaosdkdemo.domain.utils.onFailed
import com.dgparkcode.kakaosdkdemo.domain.utils.onSucceeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: UserLoginUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Idle)
    val viewState: StateFlow<LoginViewState> get() = _viewState.asStateFlow()

    fun login() = viewModelScope.launch {
        _viewState.value = LoginViewState.InProgress

        loginUseCase.invoke()
            .onSucceeded {
                _viewState.value = when (it) {
                    UserAuthState.LoginState.Canceled -> LoginViewState.Canceled
                    UserAuthState.LoginState.LoggedIn -> LoginViewState.LoggedIn
                }
            }.onFailed {
                _viewState.value = LoginViewState.Failed
            }
    }
}

sealed class LoginViewState {
    object InProgress : LoginViewState()
    object LoggedIn : LoginViewState()
    object Canceled : LoginViewState()
    object Failed : LoginViewState()
    object Idle : LoginViewState()
}