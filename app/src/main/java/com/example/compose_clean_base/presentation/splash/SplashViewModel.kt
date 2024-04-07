package com.example.compose_clean_base.presentation.splash

import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.usecase.FetchLoginUseCase
import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.data.usecase.GetCourseDataUseCase
import com.example.compose_clean_base.data.usecase.GetPasswordUseCase
import com.example.compose_clean_base.data.usecase.GetUsernameUseCase
import com.example.compose_clean_base.provider.AppResourceProvider
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUsernameUseCase: GetUsernameUseCase,
    private val getPasswordUseCase: GetPasswordUseCase,
    private val getFetchLoginUseCase: FetchLoginUseCase,
    private val getCourseDataUseCase: GetCourseDataUseCase,
    private val resourceProvider: ResourceProvider) : BaseViewModel<SplashState>() {

    override fun initialState() = SplashState()

    fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            is SplashEvent.GetCoursesData -> {
                getCoursesData()
            }
        }
    }

    private fun getCoursesData() = safeLaunch {
        executeRemoteUseCase(getCourseDataUseCase()) { res ->
            if (res.status == 200) {
                login()
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    override fun handleError(errorText: String) {
        uiState.update { it.copy(commonState = CommonState.Error(errorText)) }
    }

    private fun login() = safeLaunch {
        val username = getUsernameUseCase()
        val password = getPasswordUseCase()

        if (username.isEmpty() || password.isEmpty()) {
            uiState.update { it.copy(commonState = CommonState.Idle) }
        } else {
            executeRemoteUseCase(getFetchLoginUseCase(username, password)) { res ->
                if (res.status == 200) {
                    uiState.update { it.copy(commonState = CommonState.Success()) }
                }
            }
        }
    }
}

