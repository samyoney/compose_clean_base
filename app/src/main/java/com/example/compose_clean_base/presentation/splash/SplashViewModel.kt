package com.example.compose_clean_base.presentation.splash

import com.example.compose_clean_base.R
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.usecase.enroll.CheckDataInitializedUseCase
import com.example.compose_clean_base.data.usecase.login.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.enroll.FetchCoursesUseCase
import com.example.framework.base.BaseViewModel
import com.example.compose_clean_base.data.usecase.login.CheckLoggedInUseCase
import com.example.compose_clean_base.data.usecase.enroll.SaveCoursesUseCase
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.StateObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchAutoLoginUseCase: FetchAutoLoginUseCase,
    private val fetchCoursesUseCase: FetchCoursesUseCase,
    private val saveCoursesUseCase: SaveCoursesUseCase,
    private val checkLoggedInUseCase: CheckLoggedInUseCase,
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
            if (res.status == 0) {
                saveCourses(res)
                login()
            } else {
                handleError(resourceProvider.getString(R.string.error_api_message))
            }
        }
    }

    private fun saveCourses(res: CourseResponse) = safeLaunch {
        executeLocalUseCase(saveCoursesUseCase(res.course))
    }

    override fun handleError(errorText: String) {
        updateObservableState { StateObserver.Error(errorText) }
    }

    private fun login() = safeLaunch {
        if (checkLoggedInUseCase()) {
            onNextScreen()
        } else {
            executeRemoteUseCase(fetchAutoLoginUseCase()) { res ->
                if (res.status == 0) {
                    onNextScreen()                }
            }
        }
    }

    private fun onNextScreen() = safeLaunch {
        delay(2000)
        updateState { it.copy(isNextScreen = true) }
    }
}

