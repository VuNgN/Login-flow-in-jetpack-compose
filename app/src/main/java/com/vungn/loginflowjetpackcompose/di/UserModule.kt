package com.vungn.loginflowjetpackcompose.di

import com.vungn.loginflowjetpackcompose.util.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserStorage(): UserStorage {
        return UserStorage()
    }
}