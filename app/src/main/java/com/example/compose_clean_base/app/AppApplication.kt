package com.example.compose_clean_base.app

import com.example.compose_clean_base.BuildConfig
import com.example.framework.base.app.AppInitializer
import com.example.framework.base.app.CoreApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication : CoreApplication() {

    @Inject
    lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.init(this)
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}