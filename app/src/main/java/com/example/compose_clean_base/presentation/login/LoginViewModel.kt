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
        updateObservableState { StateObserver.Loading }
    }

    override fun handleError(errorText: String) {
        updateObservableState { StateObserver.Error(errorText) }
    }

    private fun onChangeMode() = safeLaunch {
        updateState { it.copy(isRegisterScreen = !it.isRegisterScreen) }
    }

    private fun onLogin() = safeLaunch {
        if (!checkValidation(uiState.username, uiState.password)) {
            updateObservableState {  StateObserver.Error(resourceProvider.getString(R.string.validation_fail_text)) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchLoginUseCase(uiState.username, uiState.password)
        ) { res ->
            if (res.status == 0) {
                handleAfterLogin(uiState.username, uiState.password)
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun onRegister() = safeLaunch {
        if (!checkValidation(uiState.username, uiState.password, uiState.birth)) {
            updateObservableState {  StateObserver.Error(resourceProvider.getString(R.string.validation_fail_text)) }
            return@safeLaunch
        }
        executeRemoteUseCase(
            fetchRegisterUseCase(uiState.username, uiState.password, "", uiState.name, uiState.birth)
        ) { res ->
            if (res.status == 0) {
                handleAfterLogin(uiState.username, uiState.password)
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun handleAfterLogin(username: String, password: String) = safeLaunch {
            fetchStudentsData {res ->
                saveStudents(res)
                saveAccountInfo(username, password)
                updateState {  it.copy(isNextScreen = true) }
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
            updateState {
                    it.copy(username = text.uppercase(Locale.US))
            }
        }
    }

    private fun onInputPassword(text: String) = safeLaunch {
        if ((text.isEmpty() || text.matches("^[A-Z0-9a-z]+$".toRegex())) && text.length <= 16) {
            updateState {
                it.copy(password = text.uppercase(Locale.US))
            }
        }
    }

    private fun onInputName(text: String) = safeLaunch {
        updateState {
            it.copy(name = text)
        }
    }

    private fun onInputBirth(year: Int, month: Int) = safeLaunch {
        val birthText = "$year/$month"
        updateState {
            it.copy(birth = birthText)
        }
    }

    private fun onIdle() = safeLaunch {
        updateObservableState { StateObserver.Idle }
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