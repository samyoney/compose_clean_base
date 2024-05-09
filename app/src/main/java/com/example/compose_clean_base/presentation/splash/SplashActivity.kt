package com.example.compose_clean_base.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.ErrorDialog
import com.example.compose_clean_base.app.controller.SetupSystemUi
import com.example.compose_clean_base.app.theme.BaseColor
import com.example.compose_clean_base.app.theme.PurpleBgColor
import com.example.compose_clean_base.app.theme.AppTheme
import com.example.compose_clean_base.presentation.main.MainActivity
import com.example.framework.base.CommonState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asStateFlow
import kotlin.system.exitProcess

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : FragmentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    companion object {
        var isInitialized = false
        var main: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (main != null) {
            main?.finishAndRemoveTask()
            main = null
            startActivity(intent)
            exitProcess(0)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { false }
        }
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SetupSystemUi(rememberSystemUiController(), BaseColor)
                SplashView(viewModel, onNavigateActivity = {
                    onNavigateActivity()
                }, onError = {
                    finishAndRemoveTask()
                })
            }
        }
        isInitialized = true
    }

    override fun onStart() {
        super.onStart()
        viewModel.onTriggerEvent(SplashEvent.GetCoursesData)
    }

    private fun onNavigateActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.data = this.intent.data
        startActivity(intent)
        finish()
    }
}

@Composable
private fun SplashView(viewModel: SplashViewModel, onNavigateActivity: () -> Unit, onError: () -> Unit) {
    val uiState by viewModel.uiState.asStateFlow().collectAsState()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(PurpleBgColor)
        systemUiController.setNavigationBarColor(PurpleBgColor)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PurpleBgColor
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center
        ) {
            val imageSize = maxWidth / 2
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(imageSize)
            )
        }
    }
    when (val commonState = uiState.commonState) {
        is CommonState.Idle -> {
            if (uiState.isNextToMain) {
                onNavigateActivity()
            }
        }
        is CommonState.Error ->
            ErrorDialog(
                content = commonState.mess
            ) {
                onError()
            }
        else -> {
        }

    }
}