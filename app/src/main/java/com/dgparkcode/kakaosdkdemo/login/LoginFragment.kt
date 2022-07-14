package com.dgparkcode.kakaosdkdemo.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dgparkcode.kakaosdkdemo.base.BaseFragment
import com.dgparkcode.kakaosdkdemo.data.utils.KakaoActivityContextStore
import com.dgparkcode.kakaosdkdemo.databinding.FragmentLoginBinding
import com.dgparkcode.kakaosdkdemo.ext.repeatOnStarted
import com.dgparkcode.kakaosdkdemo.viewmodel.LoginViewModel
import com.dgparkcode.kakaosdkdemo.viewmodel.LoginViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private val contextStore: KakaoActivityContextStore by lazy {
        KakaoActivityContextStore.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.repeatOnStarted {
            viewModel.viewState.collect { onLoginViewStateReceived(it) }
        }

        binding.loginBtn.setOnClickListener {
            // context settings for using kakao sdk
            contextStore.setActivityContext(requireActivity())

            viewModel.login()
        }
    }

    private fun onLoginViewStateReceived(state: LoginViewState) {
        when (state) {
            LoginViewState.Canceled -> updateLoginText("canceled")
            LoginViewState.Failed -> updateLoginText("failed")
            LoginViewState.InProgress -> updateLoginText("in progress")
            LoginViewState.Idle -> updateLoginText()
            LoginViewState.LoggedIn -> updateLoginText("logged in")
        }

        // disable button when in progress
        binding.loginBtn.isEnabled = LoginViewState.InProgress != state
    }

    private fun updateLoginText(text: CharSequence = "") {
        binding.loginText.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contextStore.recycle()
    }
}