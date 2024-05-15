package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveStudentFromCourseUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    operator fun invoke(id: String): Flow<Unit> = flow {
        val student = studentRepository.getStudent(id)
        student.courseId = null
        studentRepository.updateStudent(student)
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}
