package com.example.compose_clean_base.data

import android.annotation.SuppressLint
import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.CourseService
import com.example.compose_clean_base.data.remote.service.RegisterService
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.compose_clean_base.data.repository.StudentRepository
import com.example.compose_clean_base.data.usecase.FetchLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.SaveAccountInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideStudentRepository(
        loginService: LoginService,
        registerService: RegisterService,
        cacheManager: CacheManager,
        ) = StudentRepository(loginService, registerService, cacheManager)

    @Provides
    fun provideCourseRepository(
        service: CourseService,
        courseDao: CourseDao
    ) = CourseRepository(service, courseDao)

    @Provides
    fun provideFetchLoginUseCase(
        studentRepository: StudentRepository
    ) = FetchLoginUseCase(studentRepository)

    @Provides
    fun provideFetchRegisterUseCase(
        studentRepository: StudentRepository
    ) = FetchRegisterUseCase(studentRepository)

    @Provides
    fun provideSaveAccountInfoUseCase(
        studentRepository: StudentRepository
    ) = SaveAccountInfoUseCase(studentRepository)
}