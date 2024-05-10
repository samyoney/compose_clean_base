package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.model.remote.request.LoginRequest
import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.model.remote.request.RegisterRequest
import com.example.compose_clean_base.data.model.remote.response.LoginResponse
import com.example.compose_clean_base.data.model.remote.response.RegisterResponse
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.RegisterService
import com.example.framework.extension.fromJson
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val loginService: LoginService,
    private val registerService: RegisterService,
    private val studentDao: StudentDao,
    private val cacheManager: CacheManager
    ) {

    companion object {
        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
    }

    suspend fun login(username: String, password: String) = if (BuildConfig.DEBUG) {
        requireNotNull(cacheManager.readFromAssets("login.json").fromJson<LoginResponse>())
    } else {
        loginService.fetch(LoginRequest(username, password))
    }

    suspend fun register(username: String, password: String, courseId: String, name: String, birth: String) = if (BuildConfig.DEBUG) {
        requireNotNull(cacheManager.readFromAssets("register.json").fromJson<RegisterResponse>())
    } else {
        registerService.fetch(RegisterRequest(username, password, courseId, name, birth))
    }

    var username: String
        get() {
            return cacheManager.read(USERNAME_KEY, String())
        }
        set(value) {
            cacheManager.write(USERNAME_KEY, value)
        }

    var password: String
        get() {
            return cacheManager.read(PASSWORD_KEY, String())
        }
        set(value) {
            cacheManager.write(PASSWORD_KEY, value)
        }

    suspend fun saveListStudent(studentEntity: List<StudentEntity>) = studentDao.insert(studentEntity)
}