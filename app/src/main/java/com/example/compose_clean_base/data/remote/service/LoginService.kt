package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.data.model.remote.request.LoginRequest
import com.example.compose_clean_base.data.model.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun fetch(@Body param: LoginRequest): LoginResponse
}