package com.example.main_code.util

sealed class AppState<out T> {
    object Loading : AppState<Nothing>()
    data class Success<T>(val data: T) : AppState<T>()
    data class Error(val error: Exception) : AppState<Nothing>()
}
