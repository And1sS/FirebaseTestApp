package com.and1ss.firebasetestapp.domain

sealed class LoadingState<T> {
    class Loading<T>(val initialData: T?): LoadingState<T>()
    class SuccessfullyFinished<T>(val result: T?): LoadingState<T>()
    class Failed<T>(val error: String?): LoadingState<T>()
}