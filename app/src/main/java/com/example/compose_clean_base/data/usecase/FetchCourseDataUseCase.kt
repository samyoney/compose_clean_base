package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.framework.network.ApiState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchCourseDataUseCase @Inject constructor(private val repository: CourseRepository) {

    operator fun invoke(): Flow<ApiState<CourseResponse>> = flow {
        val result = safeFetchApi { repository.fetchCourses() }
        emit(result)
    }.flowOn(Dispatchers.IO)
}