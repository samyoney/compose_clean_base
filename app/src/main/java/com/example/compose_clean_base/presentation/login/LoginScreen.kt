package com.example.compose_clean_base.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.ErrorDialog
import com.example.compose_clean_base.app.component.ExtraLargeSpacer
import com.example.compose_clean_base.app.component.FullScreenLoading
import com.example.compose_clean_base.app.component.Toolbar
import com.example.compose_clean_base.app.theme.dimen20
import com.example.compose_clean_base.app.theme.dimen38
import com.example.compose_clean_base.presentation.login.view.LoginBirthButton
import com.example.compose_clean_base.presentation.login.view.LoginInputField
import com.example.compose_clean_base.presentation.login.view.LoginButton
import com.example.compose_clean_base.provider.mask.NavigationProvider
import com.example.framework.base.StateObserver
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { Toolbar(if (uiState.isRegisterScreen) R.string.register_nav_tab else R.string.login_nav_tab) },
        content = { padding ->
            val modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .clickable(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
            Column(modifier = modifier) {
                LoginInputField(
                    modifier = Modifier.padding(top = dimen38()),
                    label = stringResource(R.string.username),
                    value = uiState.username,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputUsername(newValue)
                        )
                    },
                    placeholder = stringResource(R.string.place_holder),
                    helperText = stringResource(R.string.description),
                )
                LoginInputField(
                    modifier = Modifier.padding(top = dimen20()),
                    label = stringResource(R.string.password),
                    value = uiState.password,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputPassword(newValue)
                        )
                    },
                    placeholder = stringResource(R.string.place_holder),
                    helperText = stringResource(R.string.description),
                )
                if (uiState.isRegisterScreen) {
                    LoginInputField(
                        modifier = Modifier.padding(top = dimen20()),
                        label = stringResource(R.string.name),
                        value = uiState.name,
                        onValueChange = { newValue ->
                            viewModel.onTriggerEvent(
                                LoginEvent.InputName(newValue)
                            )
                        },
                        placeholder = stringResource(R.string.place_holder),
                        helperText = stringResource(R.string.description),
                    )
                    LoginBirthButton(uiState) { year, month ->
                        viewModel.onTriggerEvent(LoginEvent.InputBirth(year, month))
                    }
                    ExtraLargeSpacer()
                    LoginButton(stringResource(R.string.register)) {
                        viewModel.onTriggerEvent(LoginEvent.Register)
                    }
                    ExtraLargeSpacer()
                    LoginButton(stringResource(R.string.back_to_login)) {
                        viewModel.onTriggerEvent(LoginEvent.ChangeLoginMode)
                    }

                } else {
                    ExtraLargeSpacer()
                    LoginButton(stringResource(R.string.login)) {
                        viewModel.onTriggerEvent(LoginEvent.Login)
                    }
                    ExtraLargeSpacer()
                    LoginButton(stringResource(R.string.go_to_register)) {
                        viewModel.onTriggerEvent(LoginEvent.ChangeLoginMode)
                    }

                }
            }

            when (val stateObserver = uiState.stateObserver) {
                is StateObserver.Loading -> FullScreenLoading()
                is StateObserver.Idle -> {
                    if (stateObserver.wakeUpData?.isNextScreen == true) {
                        navigator.openSam()
                    }
                }

                is StateObserver.Error -> {
                    ErrorDialog(content = stateObserver.mess) {
                        viewModel.onTriggerEvent(LoginEvent.IdleReturn)
                    }
                }

                else -> {}
            }
        }
    )
}