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
import com.example.compose_clean_base.data.usecase.enroll.AddStudentIntoCourseUseCase
import com.example.compose_clean_base.data.usecase.enroll.CheckDataInitializedUseCase
import com.example.compose_clean_base.data.usecase.login.FetchAutoLoginUseCase
import com.example.compose_clean_base.data.usecase.login.FetchLoginUseCase
import com.example.compose_clean_base.data.usecase.login.FetchRegisterUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetStudentsByCourseIdUseCase
import com.example.compose_clean_base.data.usecase.login.CheckLoggedInUseCase
import com.example.compose_clean_base.data.usecase.login.SaveAccountInfoUseCase
import com.example.compose_clean_base.data.usecase.enroll.SaveCoursesUseCase
import com.example.compose_clean_base.data.usecase.enroll.SaveStudentsUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetCoursesUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetStudentsUseCase
import com.example.compose_clean_base.data.usecase.enroll.RemoveStudentFromCourseUseCase
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
        studentDao: StudentDao
        ) = StudentRepository(studentService, studentDao)

    @Provides
    fun provideCourseRepository(
        service: CourseService,
        courseDao: CourseDao,
        ) = CourseRepository(service, courseDao)

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
    ) = GetCoursesUseCase(courseRepository)

    @Provides
    fun provideLoggedInCheckerUseCase(
        accountRepository: AccountRepository
    ) = CheckLoggedInUseCase(accountRepository)

    @Provides
    fun provideCheckDataInitializedUseCase(
        courseRepository: CourseRepository
    ) = CheckDataInitializedUseCase(courseRepository)

    @Provides
    fun provideGetStudentsUseCase(
        studentRepository: StudentRepository
    ) = GetStudentsUseCase(studentRepository)

    @Provides
    fun provideAddStudentsIntoCourseUseCase(
        studentRepository: StudentRepository
    ) = AddStudentIntoCourseUseCase(studentRepository)

    @Provides
    fun provideRemoveStudentFromCourseUseCase(
        studentRepository: StudentRepository
    ) = RemoveStudentFromCourseUseCase(studentRepository)
}