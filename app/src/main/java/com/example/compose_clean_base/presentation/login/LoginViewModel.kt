package com.example.compose_clean_base.presentation.login

import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.usecase.enroll.FetchStudentsUseCase
import com.example.compose_clean_base.data.usecase.enroll.SaveStudentsUseCase
import com.example.compose_clean_base.data.usecase.login.FetchLoginUseCase
import com.example.compose_clean_base.data.usecase.login.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.login.SaveAccountInfoUseCase
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.StateObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchRegisterUseCase: FetchRegisterUseCase,
    private val fetchLoginUseCase: FetchLoginUseCase,
    private val fetchStudentsUseCase: FetchStudentsUseCase,
    private val saveStudentsUseCase: SaveStudentsUseCase,
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
            is LoginEvent.Login -> {
                onLogin()
            }
            is LoginEvent.ChangeLoginMode -> {
                onChangeMode()
            }
            is LoginEvent.IdleReturn -> {
                onIdle()
            }
        }
    }

    override fun startLoading() {
        super.startLoading()
        uiState.update { it.copy(stateObserver = StateObserver.Loading) }
    }

    override fun handleError(errorText: String) {
        uiState.update { it.copy(stateObserver = StateObserver.Error(errorText)) }
    }

    private fun onChangeMode() = safeLaunch {
        uiState.update { it.copy(isRegisterScreen = !it.isRegisterScreen) }
    }

    private fun onLogin() = safeLaunch {
        if (!checkValidation(uiState.value.username, uiState.value.password)) {
            uiState.update { it.copy(stateObserver = StateObserver.Error(resourceProvider.getString(R.string.validation_fail_text))) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchLoginUseCase(uiState.value.username, uiState.value.password)
        ) { res ->
            if (res.status == 0) {
                val info = uiState.value
                handleAfterLogin(info.username, info.password)
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun onRegister() = safeLaunch {
        if (!checkValidation(uiState.value.username, uiState.value.password, uiState.value.birth)) {
            uiState.update { it.copy(stateObserver = StateObserver.Error(resourceProvider.getString(R.string.validation_fail_text))) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchRegisterUseCase(uiState.value.username, uiState.value.password, "", uiState.value.name, uiState.value.birth)
        ) { res ->
            if (res.status == 0) {
                val info = uiState.value
                handleAfterLogin(info.username, info.password)
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun handleAfterLogin(username: String, password: String) = safeLaunch {
            fetchStudentsData {res ->
                saveStudents(res)
                saveAccountInfo(username, password)
                uiState.update { it.copy(stateObserver = StateObserver.Idle(LoginState.IdleObserver(isNextScreen = true))) }
            }
    }

    private fun fetchStudentsData(onFinish: (res: StudentResponse) -> Unit) = safeLaunch {
        executeRemoteUseCase(fetchStudentsUseCase()) { res ->
            if (res.status == 0) {
                onFinish(res)
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun saveAccountInfo(username: String, password: String) = safeLaunch {
        executeLocalUseCase(saveAccountInfoUseCase(username, password))
    }

    private fun saveStudents(res: StudentResponse) = safeLaunch {
        executeLocalUseCase(saveStudentsUseCase(res.students))
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
        uiState.update { it.copy(stateObserver = StateObserver.Idle()) }
    }

    private fun checkValidation(username: String?, password: String?, birthDay: String? = null): Boolean {
        val isCompanyIDValid = isValidString(username)
        val isUserValid = isValidString(password)

        val isBirthdayValid = if (birthDay != null) isValidString(birthDay) else true
        return isCompanyIDValid && isUserValid && isBirthdayValid
    }

    private fun isValidString(input: String?): Boolean {
        val checkedInput = input.orEmpty()
        return checkedInput.isNotEmpty()
    }
}