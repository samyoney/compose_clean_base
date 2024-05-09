package com.example.compose_clean_base.presentation.main

import com.example.compose_clean_base.data.usecase.GetPasswordUseCase
import com.example.compose_clean_base.data.usecase.GetUsernameUseCase
import com.example.compose_clean_base.presentation.destinations.LoginScreenDestination
import com.example.compose_clean_base.presentation.destinations.SamScreenDestination
import com.example.framework.base.BaseViewModel
import com.example.framework.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUsernameUseCase: GetUsernameUseCase,
                                        private val getPasswordUseCase: GetPasswordUseCase) :
    BaseViewModel<CommonState>() {

    override fun initialState() = CommonState.Empty

    val startRoute get() = if (getUsernameUseCase().isEmpty() || getPasswordUseCase().isEmpty()) LoginScreenDestination else SamScreenDestination
}