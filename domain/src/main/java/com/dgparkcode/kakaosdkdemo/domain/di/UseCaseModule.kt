package com.dgparkcode.kakaosdkdemo.domain.di

import com.dgparkcode.kakaosdkdemo.domain.repository.UserAuthRepository
import com.dgparkcode.kakaosdkdemo.domain.usecase.UserLoginUseCase
import com.dgparkcode.kakaosdkdemo.domain.usecase.UserLogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUserLoginUseCase(
        userAuthRepository: UserAuthRepository
    ): UserLoginUseCase = UserLoginUseCase(userAuthRepository)

    @Singleton
    @Provides
    fun provideUserLogoutUseCase(
        userAuthRepository: UserAuthRepository
    ): UserLogoutUseCase = UserLogoutUseCase(userAuthRepository)
}