package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.dto.CourseDto
import com.example.compose_clean_base.data.model.dto.toEntity
import com.example.compose_clean_base.data.model.dto.toEntityList
import com.example.compose_clean_base.data.repository.CourseRepository
import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class SaveCourseUseCase @Inject constructor(private val courseRepository: CourseRepository, private val studentRepository: StudentRepository) {

    operator fun invoke(courseDto: CourseDto): Flow<Unit> = flow<Unit> {
        courseRepository.saveCourse(courseDto.toEntity())
        studentRepository.saveListStudent(courseDto.students.toEntityList())
    }.flowOn(Dispatchers.IO)
}