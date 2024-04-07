package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.model.remote.request.RegisterRequest
import com.example.compose_clean_base.data.model.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterService {
    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @POST("register")
    suspend fun fetch(@Body param: RegisterRequest): RegisterResponse
}