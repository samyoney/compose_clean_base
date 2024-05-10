package com.example.framework.base


sealed interface StateObserver<out T> {
    data object Empty : StateObserver<Nothing>
    data class Idle<out T>(val wakeUpData: T? = null) : StateObserver<T>
    data object Loading : StateObserver<Nothing>
    data class Error(val mess: String = String()) : StateObserver<Nothing>
}
