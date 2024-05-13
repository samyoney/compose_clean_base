package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class CheckDataInitializedUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<Boolean> = flow {
        emit(courseRepository.getEnrollCourse().isNotEmpty())
    }.flowOn(Dispatchers.IO)

}