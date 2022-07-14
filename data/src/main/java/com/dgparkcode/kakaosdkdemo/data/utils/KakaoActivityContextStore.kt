package com.dgparkcode.kakaosdkdemo.data.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
class KakaoActivityContextStore {

    private var context: Context? = null

    fun setActivityContext(context: Context) {
        this.context = context
    }

    fun getActivityContext() = context

    fun recycle() {
        this.context = null
    }

    companion object {
        @Volatile
        private var instance: KakaoActivityContextStore? = null

        fun getInstance(): KakaoActivityContextStore {
            return instance ?: synchronized(KakaoActivityContextStore::class.java) {
                instance ?: KakaoActivityContextStore().also {
                    instance = it
                }
            }
        }
    }
}