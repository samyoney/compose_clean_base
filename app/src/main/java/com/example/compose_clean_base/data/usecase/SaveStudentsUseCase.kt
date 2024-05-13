package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class SaveStudentsUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    operator fun invoke(student: List<StudentResponse.Student>): Flow<Unit> = flow<Unit> {
        val listStudentEntity = student.map {
            StudentEntity(courseId = it.id, name = it.name, birth = it.birth)
        }
        studentRepository.insertListStudent(listStudentEntity)
    }.flowOn(Dispatchers.IO)

}