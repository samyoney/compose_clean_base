package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import retrofit2.http.Headers
import retrofit2.http.POST

interface StudentService {
    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @POST("get/student")
    suspend fun fetch(): StudentResponse
}