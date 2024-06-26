package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.framework.network.RequestState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchCoursesUseCase @Inject constructor(private val repository: CourseRepository) {

    operator fun invoke(): Flow<RequestState<CourseResponse>> = flow {
        val result = safeFetchApi { repository.fetchCourses() }
        emit(result)
    }.flowOn(Dispatchers.IO)
}