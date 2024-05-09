package com.example.compose_clean_base.presentation.splash

import com.example.framework.base.CommonState

data class SplashState(var isNextToMain: Boolean = false, var commonState: CommonState = CommonState.Empty)

sealed class SplashEvent {
    data object GetCoursesData : SplashEvent()
}