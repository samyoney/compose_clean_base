package com.example.compose_clean_base.presentation.sam

import com.example.framework.base.CommonState

data class SamState(
    var commonState: CommonState = CommonState.Empty
)

sealed class SamEvent {
}