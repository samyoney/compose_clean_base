package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.dto.CourseDto
import com.example.compose_clean_base.data.model.dto.toDto
import com.example.compose_clean_base.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<List<CourseDto>> = flow {
        emit(courseRepository.getEnrollCourse().map { it.toDto() })
    }.flowOn(Dispatchers.IO)
}