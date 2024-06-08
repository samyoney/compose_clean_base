package com.example.framework.base


sealed interface LoadingState<out T> {
    data object Idle : LoadingState<Nothing>
    data object Loading : LoadingState<Nothing>
    data class Loaded<T>(var data: T? = null) : LoadingState<T>
    data class Error(val mess: String = String()) : LoadingState<Nothing>
}
