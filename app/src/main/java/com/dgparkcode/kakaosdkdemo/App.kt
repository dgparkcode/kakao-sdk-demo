package com.dgparkcode.kakaosdkdemo

import android.app.Application
import com.dgparkcode.kakaosdkdemo.data.utils.KakaoSdkInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdkInitializer.initialize(this)
    }
}