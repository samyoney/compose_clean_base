package com.example.buildsrc

object Versions {
    const val Compose = "1.1.1"
    const val CoreKtx = "1.12.0"
    const val Retrofit = "2.9.0"
    const val Okhttp = "4.12.0"
    const val Moshi = "1.15.1"
    const val Chucker = "3.5.2"
    const val Room = "2.6.0"
    const val Security = "1.0.0"
    const val Hilt = "2.48.1"
    const val Destination = "1.9.54"
}

object SupportLib {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val Appcompat = "androidx.appcompat:appcompat:1.4.1"
    const val Material = "com.google.android.material:material:1.5.0"
    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"

    const val Splashscreen = "androidx.core:core-splashscreen:1.0.0-beta02"
    const val Timber = "com.jakewharton.timber:timber:5.0.1"
    const val Paging = "androidx.paging:paging-runtime-ktx:3.1.1"
    const val Leak = "com.squareup.leakcanary:leakcanary-android:2.12"
}

object ComposeLib {
    const val Bom = "androidx.compose:compose-bom:2023.03.00"
    const val Ui = "androidx.compose.ui:ui"
    const val Material = "androidx.compose.material:material:1.3.1"
    const val Material3 = "androidx.compose.material3:material3"
    const val Preview = "androidx.compose.ui:ui-tooling-preview"
    const val Runtime = "androidx.compose.runtime:runtime:1.3.1"
    const val Graphics = "androidx.compose.ui:ui-graphics"
    const val Foundation = "androidx.compose.foundation:foundation:1.3.1"
    const val MaterialIconCore = "androidx.compose.material:material-icons-core:1.3.1"
    const val MaterialIconExtended = "androidx.compose.material:material-icons-extended:1.3.1"
    const val Tooling = "androidx.compose.ui:ui-tooling:1.3.1"
    const val Manifest = "androidx.compose.ui:ui-test-manifest:1.3.1"

    const val Activity = "androidx.activity:activity-compose:1.8.0"
    const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
    const val Lottie = "com.airbnb.android:lottie-compose:5.0.3"
    const val Paging = "androidx.paging:paging-compose:1.0.0-alpha14"
    const val Coil = "io.coil-kt:coil-compose:2.5.0"
}

object NavigationLib {
    const val Navigation = "androidx.navigation:navigation-compose:2.7.5"
    const val DestinationCore = "io.github.raamcosta.compose-destinations:core:${Versions.Destination}"
    const val DestinationKsp = "io.github.raamcosta.compose-destinations:ksp:${Versions.Destination}"
    const val DestinationAnimation = "io.github.raamcosta.compose-destinations:animations-core:${Versions.Destination}"
}

object AccompanistLib {
    const val Systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:0.23.1"
    const val Insets = "com.google.accompanist:accompanist-insets:0.23.1"
    const val PlaceholderMaterial = "com.google.accompanist:accompanist-placeholder-material:0.23.1"
    const val NavigationMaterial = "com.google.accompanist:accompanist-navigation-material:0.23.1"
    const val Permissions = "com.google.accompanist:accompanist-permissions:0.23.1"
}

object NetworkLib {
    const val Moshi = "com.squareup.moshi:moshi-kotlin:${Versions.Moshi}"
    const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Moshi}"
    const val MoshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.Moshi}"
    const val MoshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.Moshi}"

    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
    const val RetrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit}"
    const val Okhttp = "com.squareup.okhttp3:okhttp:${Versions.Okhttp}"
    const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp}"

    const val ChuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.Chucker}"
    const val ChuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.Chucker}"
    const val Ktor = "io.ktor:ktor-client-cio:1.6.7"
    const val KotlinXSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    const val KotlinXSerializationRetrofit = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object StorageLib {
    const val RoomRuntime = "androidx.room:room-runtime:${Versions.Room}"
    const val RoomKtx = "androidx.room:room-ktx:${Versions.Room}"
    const val RoomCompiler = "androidx.room:room-compiler:${Versions.Room}"

    const val DatastorePref = "androidx.datastore:datastore-preferences:${Versions.Security}"
    const val Datastore = "androidx.datastore:datastore:${Versions.Security}"
    const val SecurityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha06"
}

object DaggerHiltLib {
    const val Android = "com.google.dagger:hilt-android:${Versions.Hilt}"
    const val Compiler = "com.google.dagger:hilt-compiler:${Versions.Hilt}"
    const val Compose = "androidx.hilt:hilt-navigation-compose:1.1.0"
}

object FirebaseLib {
    const val Gms = "com.google.android.gms:play-services-base:18.3.0"
    const val Analytics = "com.google.firebase:firebase-analytics-ktx:21.6.1"
    const val Crashlytics = "com.google.firebase:firebase-crashlytics-ktx:18.6.3"
    const val Messaging = "com.google.firebase:firebase-messaging-ktx:23.4.1"
    const val Config = "com.google.firebase:firebase-config-ktx:21.6.3"
}

object AndroidTestingLib {
    const val Junit = "junit:junit:4.13.2"
    const val JunitExt = "androidx.test.ext:junit:1.1.5"
    const val ComposeTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:3.5.1"

    const val KotlinXTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1"
    const val Truth = "com.google.truth:truth:1.1.3"
    const val Robolectric = "org.robolectric:robolectric:4.7.3"
    const val Turbine = "app.cash.turbine:turbine:0.7.0"
    const val Mockk = "io.mockk:mockk:1.12.3"
}
