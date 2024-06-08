package com.example.compose_clean_base.presentation.splash

data class SplashState(var isNextScreen: Boolean = false)
sealed class SplashEvent {
    data object InitData : SplashEvent()
}