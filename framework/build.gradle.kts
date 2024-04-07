import com.example.buildsrc.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = Configs.NamespaceFramework
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk
        testInstrumentationRunner = Configs.AndroidJunitRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18

    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.Material)

    testImplementation(AndroidTestingLib.Junit)
    testImplementation(AndroidTestingLib.JunitExt)
    testImplementation(AndroidTestingLib.EspressoCore)
    testImplementation(AndroidTestingLib.ComposeTestJunit)

    testImplementation(AndroidTestingLib.KotlinXTest)
    testImplementation(AndroidTestingLib.Truth)
    testImplementation(AndroidTestingLib.Robolectric)
    testImplementation(AndroidTestingLib.Turbine)
    testImplementation(AndroidTestingLib.Mockk)

    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)
    implementation(SupportLib.LifecycleRuntime)

    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiKotlin)
    implementation(NetworkLib.MoshiAdapter)
    implementation(NetworkLib.Ktor)
    debugImplementation(NetworkLib.ChuckerDebug)
    releaseImplementation(NetworkLib.ChuckerRelease)

    implementation(StorageLib.RoomRuntime)
    ksp(StorageLib.RoomCompiler)
    implementation(StorageLib.RoomKtx)
    implementation(StorageLib.DatastorePref)
    implementation(StorageLib.Datastore)
    implementation(StorageLib.SecurityPref)

    implementation(SupportLib.Timber)
    implementation(SupportLib.Paging)

    implementation(FirebaseLib.Gms)
    implementation(FirebaseLib.Analytics)
    implementation(FirebaseLib.Crashlytics)
    implementation(FirebaseLib.Messaging)
    implementation(FirebaseLib.Config)
}