package com.example.compose_clean_base.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.compose_clean_base.app.controller.SetupSystemUi
import com.example.compose_clean_base.app.theme.BaseColor
import com.example.compose_clean_base.app.theme.HeaderPinkColor
import com.example.compose_clean_base.app.theme.AppTheme
import com.example.compose_clean_base.presentation.NavGraphs
import com.example.compose_clean_base.provider.AppNavigationProvider
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(),  finish: () -> Unit) {
    val navController = rememberNavController()
    val navGraph = NavGraphs.root

    val systemUiController = rememberSystemUiController()
    val navApp = remember { AppNavigationProvider(navController) }
    BackHandler { finish() }

    val interactionSource = remember { MutableInteractionSource() }

    val keyboardController = LocalSoftwareKeyboardController.current
    val dismissKeyboardModifier = Modifier
        .fillMaxSize()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            keyboardController?.hide()
        }

    AppTheme {
        SetupSystemUi(systemUiController, HeaderPinkColor)

        Surface(
            modifier = dismissKeyboardModifier,
            color = BaseColor
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = navGraph,
                startRoute = viewModel.startRoute,
                dependenciesContainerBuilder = {
                    dependency(navApp)
                }
            )
        }
    }
}