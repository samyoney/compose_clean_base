import com.example.buildsrc.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = Configs.Namespace
    compileSdk = Configs.CompileSdk

    defaultConfig {
        applicationId = Configs.Id
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.VersionCode
        versionName = Configs.VersionName

        testInstrumentationRunner = Configs.AndroidJunitRunner

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${Configs.Release.BaseUrl}\"")
            buildConfigField("String", "DB_NAME", "\"${Configs.Release.DbName}\"")
            buildConfigField("String", "API_KEY", "\"${Configs.Release.ApiKey}\"")
            buildConfigField("String", "LICENSE_KEY", "\"${Configs.Release.LicenseKey}\"")
        }

        debug {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${Configs.Debug.BaseUrl}\"")
            buildConfigField("String", "DB_NAME", "\"${Configs.Debug.DbName}\"")
            buildConfigField("String", "API_KEY", "\"${Configs.Debug.ApiKey}\"")
            buildConfigField("String", "LICENSE_KEY", "\"${Configs.Debug.LicenseKey}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18

    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(platform(ComposeLib.Bom))
    androidTestImplementation(platform(ComposeLib.Bom))
    implementation(ComposeLib.Ui)
    implementation(ComposeLib.Preview)
    debugImplementation(ComposeLib.Tooling)
    debugImplementation(ComposeLib.Manifest)
    implementation(ComposeLib.Activity)
    implementation(ComposeLib.Material)
    implementation(ComposeLib.Material3)
    implementation(ComposeLib.Runtime)
    implementation(ComposeLib.Graphics)

    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.LifecycleRuntime)

    testImplementation(AndroidTestingLib.Junit)
    androidTestImplementation(AndroidTestingLib.JunitExt)
    androidTestImplementation(AndroidTestingLib.EspressoCore)
    androidTestImplementation(AndroidTestingLib.ComposeTestJunit)

    implementation(AccompanistLib.Systemuicontroller)
    implementation(AccompanistLib.Insets)
    implementation(AccompanistLib.PlaceholderMaterial)
    implementation(AccompanistLib.NavigationMaterial)
    implementation(AccompanistLib.Permissions)

    implementation(NavigationLib.Navigation)
    implementation(NavigationLib.DestinationCore)
    ksp(NavigationLib.DestinationKsp)
    implementation(NavigationLib.DestinationAnimation)

    implementation(DaggerHiltLib.Android)
    ksp(DaggerHiltLib.Compiler)
    implementation(DaggerHiltLib.Compose)

    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiKotlin)
    implementation(NetworkLib.MoshiAdapter)
    debugImplementation(NetworkLib.ChuckerDebug)
    releaseImplementation(NetworkLib.ChuckerRelease)

    implementation(StorageLib.RoomRuntime)
    ksp(StorageLib.RoomCompiler)
    implementation(StorageLib.RoomKtx)

    implementation(SupportLib.Timber)
    debugImplementation(SupportLib.Leak)
    implementation(SupportLib.Splashscreen)

    implementation(ComposeLib.Coil)
    implementation(project(mapOf("path" to ":framework")))
}