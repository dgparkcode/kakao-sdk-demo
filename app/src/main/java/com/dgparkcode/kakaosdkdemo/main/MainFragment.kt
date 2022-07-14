package com.dgparkcode.kakaosdkdemo.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dgparkcode.kakaosdkdemo.R
import com.dgparkcode.kakaosdkdemo.base.BaseFragment
import com.dgparkcode.kakaosdkdemo.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }
}