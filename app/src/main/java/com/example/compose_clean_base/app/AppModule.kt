package com.example.compose_clean_base.app

import android.content.Context
import com.example.compose_clean_base.provider.AppResourceProvider
import com.example.compose_clean_base.provider.mask.ResourceProvider
import com.example.framework.base.app.AppInitializer
import com.example.framework.base.app.AppInitializerImpl
import com.example.framework.base.app.NetworkConfig
import com.example.framework.base.app.TimberInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(): AppApplication {
        return AppApplication()
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return AppNetworkConfig()
    }

    @Provides
    @Singleton
    fun provideTimberInitializer(
        networkConfig: NetworkConfig
    ) = TimberInitializer(networkConfig.isDev())

    @Provides
    @Singleton
    fun provideAppInitializer(
        timberInitializer: TimberInitializer
    ): AppInitializer {
        return AppInitializerImpl(timberInitializer)
    }

    @Provides
    @Singleton
    fun provideAppResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return AppResourceProvider(context)
    }
}