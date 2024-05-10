package com.example.compose_clean_base.presentation.splash

import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.model.dto.toCourseDtoList
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.usecase.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchCourseDataUseCase
import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.data.usecase.LoggedInCheckerUseCase
import com.example.compose_clean_base.data.usecase.SaveCourseUseCase
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.StateObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loggedInCheckerUseCase: LoggedInCheckerUseCase,
    private val fetchAutoLoginUseCase: FetchAutoLoginUseCase,
    private val fetchCourseDataUseCase: FetchCourseDataUseCase,
    private val saveCourseUseCase: SaveCourseUseCase,
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
        executeRemoteUseCase(fetchCourseDataUseCase()) { res ->
            if (res.status == 200) {
                saveCourse(res)
                login()
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun saveCourse(res: CourseResponse) = safeLaunch {
        val data = res.toCourseDtoList()
        data.forEach {
            executeLocalUseCase(saveCourseUseCase(it))
        }
    }

    override fun handleError(errorText: String) {
        uiState.update { it.copy(stateObserver = StateObserver.Error(errorText)) }
    }

    private fun login() = safeLaunch {
        if (loggedInCheckerUseCase()) {
            uiState.update { it.copy(
                stateObserver = StateObserver.Idle(SplashState.IdleObserver(isNextScreen = true))) }
        } else {
            executeRemoteUseCase(fetchAutoLoginUseCase()) { res ->
                if (res.status == 200) {
                    uiState.update { it.copy(
                        stateObserver = StateObserver.Idle(SplashState.IdleObserver(isNextScreen = true))) }
                }
            }
        }
    }
}

