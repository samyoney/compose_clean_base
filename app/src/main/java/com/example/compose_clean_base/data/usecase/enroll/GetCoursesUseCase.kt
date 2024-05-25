package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.dto.CourseDto
import com.example.compose_clean_base.data.model.dto.toDto
import com.example.compose_clean_base.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<List<CourseDto>> = flow {
        emit(courseRepository.getEnrollCourse().map {
            it.course.toDto()
        })
    }.flowOn(Dispatchers.IO)
}
