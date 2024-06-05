package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import retrofit2.http.GET

interface CourseService {
    @GET("courses")
    suspend fun fetch(): CourseResponse
}