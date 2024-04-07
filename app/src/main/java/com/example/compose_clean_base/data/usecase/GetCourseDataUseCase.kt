package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.remote.response.BaseResponse
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.framework.network.ApiState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCourseDataUseCase @Inject constructor(private val repository: CourseRepository) {

    operator fun invoke(): Flow<ApiState<BaseResponse<CourseResponse>>> = flow {
        val result = safeFetchApi { repository.getCoursesData() }
        emit(result)
    }.flowOn(Dispatchers.IO)
}