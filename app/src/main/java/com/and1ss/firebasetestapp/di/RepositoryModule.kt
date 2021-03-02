package com.and1ss.firebasetestapp.di

import com.and1ss.firebasetestapp.cache.LocalCache
import com.and1ss.firebasetestapp.repository.MessageRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun getMessageRepository(
        localCache: LocalCache,
        database: FirebaseDatabase
    ): MessageRepository = MessageRepository(localCache, database)
}