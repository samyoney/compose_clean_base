package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddStudentIntoCourseUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String, courseId: String): Flow<Unit> = flow {
        val student = studentRepository.getStudent(id)
        student.courseId = courseId
        studentRepository.updateStudent(student)
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}
