package com.example.compose_clean_base.presentation.splash

import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.usecase.CheckDataInitializedUseCase
import com.example.compose_clean_base.data.usecase.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchCoursesUseCase
import com.example.compose_clean_base.data.usecase.FetchStudentsUseCase
import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.data.usecase.LoggedInCheckerUseCase
import com.example.compose_clean_base.data.usecase.SaveCoursesUseCase
import com.example.compose_clean_base.data.usecase.SaveStudentsUseCase
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.StateObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loggedInCheckerUseCase: LoggedInCheckerUseCase,
    private val fetchAutoLoginUseCase: FetchAutoLoginUseCase,
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val fetchStudentsUseCase: FetchStudentsUseCase,
    private val saveCoursesUseCase: SaveCoursesUseCase,
    private val saveStudentsUseCase: SaveStudentsUseCase,
    private val checkDataInitializedUseCase: CheckDataInitializedUseCase,
    private val resourceProvider: ResourceProvider) : BaseViewModel<SplashState>() {

    override fun initialState() = SplashState()

    fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            is SplashEvent.InitData -> {
                onInitializedData()
            }
        }
    }

    private fun onInitializedData() = safeLaunch {
        executeLocalUseCase(checkDataInitializedUseCase()) { isDataInitialized ->
            if (isDataInitialized) {
                login()
            } else {
                fetchCoursesData()
            }
        }
    }

    private fun fetchCoursesData() = safeLaunch {
        executeRemoteUseCase(fetchCoursesUseCase()) { res ->
            if (res.status == 200) {
                saveCourses(res)
                fetchStudentsData()
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun fetchStudentsData() = safeLaunch {
        executeRemoteUseCase(fetchStudentsUseCase()) { res ->
            if (res.status == 200) {
                saveStudents(res)
                login()
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun saveStudents(res: StudentResponse) = safeLaunch {
        executeLocalUseCase(saveStudentsUseCase(res.students))
    }

    private fun saveCourses(res: CourseResponse) = safeLaunch {
        executeLocalUseCase(saveCoursesUseCase(res.course))
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

