package com.example.compose_clean_base.data

import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.CourseService
import com.example.compose_clean_base.data.remote.service.RegisterService
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.compose_clean_base.data.repository.StudentRepository
import com.example.compose_clean_base.data.usecase.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.GetCourseUseCase
import com.example.compose_clean_base.data.usecase.LoggedInCheckerUseCase
import com.example.compose_clean_base.data.usecase.SaveAccountInfoUseCase
import com.example.compose_clean_base.data.usecase.SaveCourseUseCase
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
        studentDao: StudentDao
        ) = StudentRepository(loginService, registerService, studentDao, cacheManager)

    @Provides
    fun provideCourseRepository(
        service: CourseService,
        courseDao: CourseDao,
        cacheManager: CacheManager
        ) = CourseRepository(service, courseDao, cacheManager)

    @Provides
    fun provideFetchLoginUseCase(
        studentRepository: StudentRepository
    ) = FetchLoginUseCase(studentRepository)

    @Provides
    fun provideFetchAutoLoginUseCase(
        studentRepository: StudentRepository
    ) = FetchAutoLoginUseCase(studentRepository)

    @Provides
    fun provideFetchRegisterUseCase(
        studentRepository: StudentRepository
    ) = FetchRegisterUseCase(studentRepository)

    @Provides
    fun provideSaveAccountInfoUseCase(
        studentRepository: StudentRepository
    ) = SaveAccountInfoUseCase(studentRepository)

    @Provides
    fun provideSaveCourseUseCase(
        courseRepository: CourseRepository,
        studentRepository: StudentRepository
    ) = SaveCourseUseCase(courseRepository, studentRepository)

    @Provides
    fun provideGetEnrollCourseUseCase(
        courseRepository: CourseRepository
    ) = GetCourseUseCase(courseRepository)

    @Provides
    fun provideLoggedInCheckerUseCase(
        studentRepository: StudentRepository
    ) = LoggedInCheckerUseCase(studentRepository)
}