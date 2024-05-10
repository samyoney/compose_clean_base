package com.example.compose_clean_base.presentation.main

import com.example.compose_clean_base.data.usecase.LoggedInCheckerUseCase
import com.example.compose_clean_base.presentation.destinations.LoginScreenDestination
import com.example.compose_clean_base.presentation.destinations.SamScreenDestination
import com.example.framework.base.BaseViewModel
import com.example.framework.base.StateObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loggedInCheckerUseCase: LoggedInCheckerUseCase) :
    BaseViewModel<StateObserver<Nothing>>() {

    override fun initialState() = StateObserver.Empty

    val startRoute get() = if (loggedInCheckerUseCase()) LoginScreenDestination else SamScreenDestination
}