package com.example.compose_clean_base.presentation.main

import androidx.lifecycle.ViewModel
import com.example.compose_clean_base.data.usecase.login.CheckLoggedInUseCase
import com.example.compose_clean_base.presentation.destinations.LoginScreenDestination
import com.example.compose_clean_base.presentation.destinations.SamScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val checkLoggedInUseCase: CheckLoggedInUseCase): ViewModel() {
    val startRoute get() = if (checkLoggedInUseCase()) SamScreenDestination else LoginScreenDestination
}