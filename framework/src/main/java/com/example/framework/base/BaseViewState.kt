package com.example.framework.base

 interface BaseViewState<T> {
     var commonState: CommonState<T>
 }

sealed interface CommonState<out T> {
    data object Idle : CommonState<Nothing>
    data object Loading : CommonState<Nothing>
    data class Success<T>(val data: T? = null) : CommonState<T>
    data class Error(val mess: String = String()) : CommonState<Nothing>
}
