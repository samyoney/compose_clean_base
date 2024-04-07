package com.example.compose_clean_base.presentation.splash

import com.example.framework.base.BaseViewState
import com.example.framework.base.CommonState


data class SplashState(override var commonState: CommonState<Nothing> = CommonState.Idle): BaseViewState<Nothing>

sealed class SplashEvent {
    data object GetCoursesData : SplashEvent()
}