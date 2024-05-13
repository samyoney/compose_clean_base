package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.dto.StudentDto
import com.example.compose_clean_base.data.model.dto.toDtoList
import com.example.compose_clean_base.data.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStudentsByCourseIdUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(courseId: String): Flow<List<StudentDto>> = flow {
        emit(courseRepository.getEnrollCourse().first { it.course.id == courseId }.students.toDtoList())
    }.flowOn(Dispatchers.IO)
}
