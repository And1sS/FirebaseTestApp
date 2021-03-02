package com.and1ss.firebasetestapp.di

import com.and1ss.firebasetestapp.cache.LocalCache
import com.and1ss.firebasetestapp.cache.LocalCacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @Provides
    @Singleton
    fun getLocalStorage(): LocalCache = LocalCacheImpl()
}