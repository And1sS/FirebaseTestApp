package com.and1ss.firebasetestapp.repository

import com.and1ss.firebasetestapp.cache.LocalCache
import com.and1ss.firebasetestapp.domain.LoadingState
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessageRepository @Inject constructor(
        private val localCache: LocalCache,
        private val database: FirebaseDatabase
) {
    suspend fun saveMessage(path: String, message: String) = withContext(Dispatchers.IO) {
        localCache.saveMessage(path, message)
        database.getReference(path).also {
            it.setValue(message)
        }
    }

    fun getMessage(path: String) = callbackFlow<LoadingState<String>> {
        val localMessage = localCache.getMessage(path)
        offer(LoadingState.Loading(localMessage))

        database.getReference(path).get().addOnCompleteListener { response ->
            if (response.isSuccessful) {
                val remoteMessage = (response.result?.value as String?)?.also { message ->
                    localCache.saveMessage(path, message)
                }

                offer(LoadingState.SuccessfullyFinished(remoteMessage))
            } else {
                offer(LoadingState.Failed(response.exception?.message))
            }
            channel.close()
        }

        awaitClose { /* here i should unsubscribe listener, but firebase does not support this O_o */ }
    }
}