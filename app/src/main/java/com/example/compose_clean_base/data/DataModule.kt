package com.example.compose_clean_base.data

import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.CourseService
import com.example.compose_clean_base.data.remote.service.RegisterService
import com.example.compose_clean_base.data.remote.service.StudentService
import com.example.compose_clean_base.data.repository.AccountRepository
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.compose_clean_base.data.repository.StudentRepository
import com.example.compose_clean_base.data.usecase.CheckDataInitializedUseCase
import com.example.compose_clean_base.data.usecase.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchLoginUseCase
import com.example.compose_clean_base.data.usecase.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.GetStudentsByCourseIdUseCase
import com.example.compose_clean_base.data.usecase.LoggedInCheckerUseCase
import com.example.compose_clean_base.data.usecase.SaveAccountInfoUseCase
import com.example.compose_clean_base.data.usecase.SaveCoursesUseCase
import com.example.compose_clean_base.data.usecase.SaveStudentsUseCase
import com.example.compose_clean_base.data.usecase.SearchCourseByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAccountRepository(
        loginService: LoginService,
        registerService: RegisterService,
        cacheManager: CacheManager
    ) = AccountRepository(loginService, registerService, cacheManager)

    @Provides
    fun provideStudentRepository(
        studentService: StudentService,
        cacheManager: CacheManager,
        studentDao: StudentDao
        ) = StudentRepository(studentService, studentDao, cacheManager)

    @Provides
    fun provideCourseRepository(
        service: CourseService,
        courseDao: CourseDao,
        cacheManager: CacheManager
        ) = CourseRepository(service, courseDao, cacheManager)

    @Provides
    fun provideFetchLoginUseCase(
        accountRepository: AccountRepository
    ) = FetchLoginUseCase(accountRepository)

    @Provides
    fun provideFetchAutoLoginUseCase(
        accountRepository: AccountRepository
    ) = FetchAutoLoginUseCase(accountRepository)

    @Provides
    fun provideFetchRegisterUseCase(
        accountRepository: AccountRepository
    ) = FetchRegisterUseCase(accountRepository)

    @Provides
    fun provideSaveAccountInfoUseCase(
        accountRepository: AccountRepository
    ) = SaveAccountInfoUseCase(accountRepository)

    @Provides
    fun provideSaveCourseUseCase(
        courseRepository: CourseRepository)
    = SaveCoursesUseCase(courseRepository)

    @Provides
    fun provideSaveStudentsUseCase(
        studentRepository: StudentRepository
    ) = SaveStudentsUseCase(studentRepository)

    @Provides
    fun provideGetEnrollCourseUseCase(
        courseRepository: CourseRepository
    ) = GetStudentsByCourseIdUseCase(courseRepository)

    @Provides
    fun provideSearchCourseByNameUseCase(
        courseRepository: CourseRepository
    ) = SearchCourseByNameUseCase(courseRepository)

    @Provides
    fun provideLoggedInCheckerUseCase(
        accountRepository: AccountRepository
    ) = LoggedInCheckerUseCase(accountRepository)

    @Provides
    fun provideCheckDataInitializedUseCase(
        courseRepository: CourseRepository
    ) = CheckDataInitializedUseCase(courseRepository)
}