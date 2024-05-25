package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.repository.StudentRepository
import com.example.framework.network.ApiState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchStudentsUseCase @Inject constructor(private val repository: StudentRepository) {

    operator fun invoke(): Flow<ApiState<StudentResponse>> = flow {
        val result = safeFetchApi { repository.fetchStudents() }
        emit(result)
    }.flowOn(Dispatchers.IO)
}