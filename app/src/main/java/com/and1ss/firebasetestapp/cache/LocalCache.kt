package com.and1ss.firebasetestapp.cache

interface LocalCache {
    fun saveMessage(path: String, message: String)
    fun getMessage(path: String): String?
}