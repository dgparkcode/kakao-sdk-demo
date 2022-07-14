package com.dgparkcode.kakaosdkdemo.data.di

import com.dgparkcode.kakaosdkdemo.data.repository.UserAuthRepositoryImpl
import com.dgparkcode.kakaosdkdemo.data.source.UserAuthDataSource
import com.dgparkcode.kakaosdkdemo.data.utils.AppErrorMapperImpl
import com.dgparkcode.kakaosdkdemo.domain.error.AppErrorMapper
import com.dgparkcode.kakaosdkdemo.domain.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAppErrorMapper(): AppErrorMapper = AppErrorMapperImpl()

    @Singleton
    @Provides
    fun provideUserAuthRepository(
        userAuthDataSource: UserAuthDataSource,
        errorMapper: AppErrorMapper
    ): UserAuthRepository = UserAuthRepositoryImpl(userAuthDataSource, errorMapper)
}