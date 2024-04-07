package com.example.compose_clean_base.presentation.sam

import com.example.framework.base.BaseViewState
import com.example.framework.base.CommonState

data class SamState(
    override var commonState: CommonState<Nothing> = CommonState.Idle
): BaseViewState<Nothing>

sealed class SamEvent {
}