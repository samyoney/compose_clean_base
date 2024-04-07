package com.example.compose_clean_base.app

import com.example.compose_clean_base.BuildConfig
import com.example.framework.base.app.NetworkConfig


class AppNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}