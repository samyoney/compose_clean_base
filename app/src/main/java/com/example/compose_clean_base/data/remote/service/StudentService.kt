package com.example.compose_clean_base.data.remote.service

import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import retrofit2.http.GET

interface StudentService {
    @GET("students")
    suspend fun fetch(): StudentResponse
}