package com.and1ss.firebasetestapp.cache

class LocalCacheImpl : LocalCache { // TODO: replace with Room?
    private val messages = hashMapOf<String, String>()

    override fun saveMessage(path: String, message: String) {
        messages[path] = message
    }

    override fun getMessage(path: String): String? {
        return messages[path]
    }
}