package com.example.framework.network.environment

import androidx.annotation.IntDef
import com.example.framework.network.environment.Environment.Companion.DEVELOPMENT
import com.example.framework.network.environment.Environment.Companion.PRODUCTION
import com.example.framework.network.environment.Environment.Companion.STAGING

@IntDef(DEVELOPMENT, STAGING, PRODUCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Environment {
    companion object {
        const val DEVELOPMENT = 1
        const val STAGING = 2
        const val PRODUCTION = 3
    }
}