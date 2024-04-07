package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.data.model.remote.request.LoginRequest
import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.model.remote.request.RegisterRequest
import com.example.compose_clean_base.data.remote.service.LoginService
import com.example.compose_clean_base.data.remote.service.RegisterService
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val loginService: LoginService,
    private val registerService: RegisterService,
    private val cacheManager: CacheManager
    ) {

    companion object {
        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
    }

    suspend fun login(username: String, password: String) =
        loginService.fetch(LoginRequest(username, password))

    suspend fun register(username: String, password: String, courseId: String, name: String, birth: String) =
        registerService.fetch(RegisterRequest(username, password, courseId, name, birth))

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
}