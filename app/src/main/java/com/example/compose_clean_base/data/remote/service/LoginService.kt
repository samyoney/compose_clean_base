package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.model.remote.response.BaseResponse
import com.example.compose_clean_base.data.model.remote.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @POST("login")
    suspend fun fetch(@Body param: LoginRequest): BaseResponse<*>
}