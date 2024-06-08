package com.example.framework.network

sealed class RequestState<out T> {
    data class Success<out T>(val result: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Error -> "Error[exception=$error]"
        }
    }
}