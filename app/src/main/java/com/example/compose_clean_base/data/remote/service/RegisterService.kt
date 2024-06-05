package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.data.model.remote.request.RegisterRequest
import com.example.compose_clean_base.data.model.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun fetch(@Body param: RegisterRequest): RegisterResponse
}