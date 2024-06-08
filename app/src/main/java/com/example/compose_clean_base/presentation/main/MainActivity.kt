package com.example.compose_clean_base.presentation.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose_clean_base.presentation.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplashActivity.main = this
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        setContent {
            MainScreen {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(200)
                    finishAndRemoveTask()
                    exitProcess(0)
                }
            }
        }
    }
}