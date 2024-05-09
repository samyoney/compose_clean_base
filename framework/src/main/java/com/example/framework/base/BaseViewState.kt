package com.example.framework.base


sealed interface CommonState {
    data object Empty : CommonState
    data object Idle : CommonState
    data object Loading : CommonState
    data class Error(val mess: String = String()) : CommonState
}
