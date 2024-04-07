package com.example.framework.network

sealed class ApiState<out T> {
    data class Success<out T>(val result: T) : ApiState<T>()
    data class Error(val error: Throwable) : ApiState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Error -> "Error[exception=$error]"
        }
    }
}