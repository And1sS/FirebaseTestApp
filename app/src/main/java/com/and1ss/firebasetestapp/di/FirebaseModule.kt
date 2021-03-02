package com.and1ss.firebasetestapp.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun getFirebaseDatabase(): FirebaseDatabase =
        FirebaseDatabase.getInstance("https://fir-test-app-80ff5-default-rtdb.firebaseio.com/")
}