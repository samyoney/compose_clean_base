package com.example.compose_clean_base.data.usecase.enroll

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
        val enrollCourse = courseRepository.getEnrollCourse()
        val listStudentDto = enrollCourse.first { it.course.id.contentEquals(courseId) }.students.toDtoList()
        emit(listStudentDto)
    }.flowOn(Dispatchers.IO)
}
