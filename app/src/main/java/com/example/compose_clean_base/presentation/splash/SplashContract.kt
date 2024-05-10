package com.example.compose_clean_base.presentation.splash

import com.example.framework.base.StateObserver


data class SplashState(var stateObserver: StateObserver<IdleObserver> = StateObserver.Empty) {
    data class IdleObserver(var isNextScreen: Boolean = false)
}

sealed class SplashEvent {
    data object GetCoursesData : SplashEvent()
}