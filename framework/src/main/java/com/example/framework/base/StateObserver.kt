package com.example.framework.base


sealed interface StateObserver<out T> {
    data object Idle : StateObserver<Nothing>
    data object Loading : StateObserver<Nothing>
    data class Update<out T>(val data: T) : StateObserver<T>
    data class Error(val mess: String = String()) : StateObserver<Nothing>
}
