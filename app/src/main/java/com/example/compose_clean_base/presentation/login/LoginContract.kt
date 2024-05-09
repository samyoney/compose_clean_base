package com.example.compose_clean_base.presentation.login

import com.example.framework.base.CommonState


data class LoginState(
    var username: String = String(),
    var password: String = String(),
    var name: String = String(),
    var birth: String = String(),
    var commonState: CommonState = CommonState.Empty
    )

sealed class LoginEvent {
    data class InputUsername(var text: String): LoginEvent()
    data class InputPassword(var text: String): LoginEvent()
    data class InputName(var text: String): LoginEvent()
    data class InputBirth(var year: Int, var month: Int): LoginEvent()
    data object Register: LoginEvent()
    data object IdleReturn: LoginEvent()
}