package com.example.buildsrc

object Configs {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.${versionPatch}"
    }

    const val Namespace = "com.example.compose_clean_base"
    const val Id = "com.example.compose_clean_base"
    const val NamespaceFramework = "com.example.framework"

    val VersionCode = generateVersionCode()
    val VersionName = generateVersionName()
    const val MinSdk = 28
    const val TargetSdk = 34
    const val CompileSdk = 34
    const val KotlinCompilerExtensionVersion = "1.5.3"


    const val AndroidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val FreeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
        "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
    )

    object Release {
        const val BaseUrl = "https://ibn2ef5etg.execute-api.ap-northeast-1.amazonaws.com/prd/"
        const val DbName = "DB_RELEASE"
        const val ApiKey = "ZSAEsqtDIYaWxVPYHh4av1m3sXRgrHRb9G5hU6Zj"
        const val LicenseKey = "2rNBCbhAxtwXunYHpqJkM4v61mTRIfU3yoj7FgP0"
    }

    object Debug {
        const val BaseUrl = "https://gtwnd2x4a7.execute-api.ap-northeast-1.amazonaws.com/dev/"
        const val DbName = "DB_DEBUG"
        const val ApiKey = "e4A6UfnYO4aKtrL5UiCRY1F0RSWFOhnz8jOTSTPB"
        const val LicenseKey = "2rNBCbhAxtwXunYHpqJkM4v61mTRIfU3yoj7FgP0"
    }
}