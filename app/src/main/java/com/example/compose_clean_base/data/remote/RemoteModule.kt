package com.example.compose_clean_base.data.remote

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.app.AppApplication
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.CourseService
import com.example.compose_clean_base.data.remote.service.RegisterService
import com.example.compose_clean_base.data.remote.service.StudentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(application: AppApplication) = if (application.isDev()) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    fun provideRegisterService(retrofit: Retrofit): RegisterService =
        retrofit.create(RegisterService::class.java)

    @Provides
    fun provideCourseService(retrofit: Retrofit): CourseService =
        retrofit.create(CourseService::class.java)

    @Provides
    fun provideStudentService(retrofit: Retrofit): StudentService =
        retrofit.create(StudentService::class.java)

}