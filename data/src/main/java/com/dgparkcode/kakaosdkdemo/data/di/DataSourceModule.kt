package com.dgparkcode.kakaosdkdemo.data.di

import com.dgparkcode.kakaosdkdemo.data.source.UserAuthDataSource
import com.dgparkcode.kakaosdkdemo.data.source.UserAuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideUserAuthDataSource(): UserAuthDataSource = UserAuthDataSourceImpl()
}