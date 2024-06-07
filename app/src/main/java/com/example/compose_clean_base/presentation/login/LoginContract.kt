package com.example.compose_clean_base.presentation.login

data class LoginState(
    var username: String = String(),
    var password: String = String(),
    var name: String = String(),
    var birth: String = String(),
    var isRegisterScreen: Boolean = false,
    var isNextScreen: Boolean = false
    )

sealed class LoginEvent {
    data class InputUsername(var text: String): LoginEvent()
    data class InputPassword(var text: String): LoginEvent()
    data class InputName(var text: String): LoginEvent()
    data class InputBirth(var year: Int, var month: Int): LoginEvent()

    data object ChangeLoginMode: LoginEvent()

    data object Register: LoginEvent()
    data object Login: LoginEvent()
    data object IdleReturn: LoginEvent()
}