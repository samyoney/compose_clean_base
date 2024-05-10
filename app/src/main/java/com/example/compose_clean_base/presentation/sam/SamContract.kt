package com.example.compose_clean_base.presentation.sam

import com.example.framework.base.StateObserver

data class SamState(
    var stateObserver: StateObserver<Nothing> = StateObserver.Empty
)

sealed class SamEvent {
}