package com.example.framework.base.app

import timber.log.Timber

class TimberInitializer(private val isDev: Boolean) : AppInitializer {
    override fun init(application: CoreApplication) {
        if (isDev) {
            Timber.plant(Timber.DebugTree())
        }
    }
}