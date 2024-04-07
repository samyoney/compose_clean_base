package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.model.remote.response.BaseResponse
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import retrofit2.http.Headers
import retrofit2.http.POST

interface CourseService {
    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @POST("get/courses")
    suspend fun fetch(): BaseResponse<CourseResponse>
}