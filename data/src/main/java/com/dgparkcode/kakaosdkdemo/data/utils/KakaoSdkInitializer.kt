package com.dgparkcode.kakaosdkdemo.data.utils

import android.content.Context
import com.dgparkcode.kakaosdkdemo.data.R
import com.kakao.sdk.common.KakaoSdk

object KakaoSdkInitializer {

    fun initialize(appContext: Context) {
        KakaoSdk.init(
            context = appContext,
            appKey = appContext.getString(R.string.kakao_app_key)
        )
    }
}