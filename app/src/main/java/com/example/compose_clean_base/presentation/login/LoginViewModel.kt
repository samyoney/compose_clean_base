package com.example.compose_clean_base.presentation.login

import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.usecase.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.SaveAccountInfoUseCase
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchRegisterUseCase: FetchRegisterUseCase,
    private val saveAccountInfoUseCase: SaveAccountInfoUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel<LoginState>() {

    override fun initialState() = LoginState()

    fun onTriggerEvent(eventType: LoginEvent) {
        when (eventType) {
            is LoginEvent.InputUsername -> {
                onInputUsername(eventType.text)
            }
            is LoginEvent.InputPassword -> {
                onInputPassword(eventType.text)
            }
            is LoginEvent.InputName -> {
                onInputName(eventType.text)
            }
            is LoginEvent.InputBirth -> {
                onInputBirth(eventType.year, eventType.month)
            }
            is LoginEvent.Register -> {
                onRegister()
            }
            is LoginEvent.IdleReturn -> {
                onIdle()
            }
        }
    }

    override fun startLoading() {
        super.startLoading()
        uiState.update { it.copy(commonState = CommonState.Loading) }
    }

    override fun handleError(errorText: String) {
        uiState.update { it.copy(commonState = CommonState.Error(errorText)) }
    }

    private fun onRegister() = safeLaunch {
        if (!checkValidation(uiState.value.username, uiState.value.password, uiState.value.birth)) {
            uiState.update { it.copy(commonState = CommonState.Error(resourceProvider.getString(R.string.validation_fail_text))) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchRegisterUseCase(uiState.value.username, uiState.value.password, "", uiState.value.name, uiState.value.birth)
        ) { res ->
            if (res.status == 200) {
                val info = uiState.value
                onSaveAccountInfo(info.username, info.password) {
                    uiState.update {
                        it.copy(commonState = CommonState.Idle)
                    }
                }
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun onSaveAccountInfo(username: String, password: String, onFinished: () -> Unit) = safeLaunch {
        executeLocalUseCase(saveAccountInfoUseCase(username, password)) {
            onFinished()
        }
    }

    private fun onInputUsername(text: String) = safeLaunch {
        if ((text.isEmpty() || text.matches("^[A-Z0-9a-z]+$".toRegex())) && text.length <= 16) {
            uiState.update {
                    it.copy(username = text.uppercase(Locale.US))
            }
        }
    }

    private fun onInputPassword(text: String) = safeLaunch {
        if ((text.isEmpty() || text.matches("^[A-Z0-9a-z]+$".toRegex())) && text.length <= 16) {
            uiState.update {
                it.copy(password = text.uppercase(Locale.US))
            }
        }
    }

    private fun onInputName(text: String) = safeLaunch {
        uiState.update {
            it.copy(name = text)
        }
    }

    private fun onInputBirth(year: Int, month: Int) = safeLaunch {
        val birthText = "$year/$month"
        uiState.update {
            it.copy(birth = birthText)
        }
    }

    private fun onIdle() = safeLaunch {
        uiState.update { it.copy(commonState = CommonState.Idle) }
    }

    private fun checkValidation(companyID: String?, userID: String?, birthDay: String?): Boolean {
        val isCompanyIDValid = isValidString(companyID)
        val isUserValid = isValidString(userID)
        val isBirthdayValid = isValidString(birthDay)

        return isCompanyIDValid && isUserValid && isBirthdayValid
    }

    private fun isValidString(input: String?): Boolean {
        val checkedInput = input.orEmpty()
        return checkedInput.isNotEmpty()
    }
}