package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

class SaveStudentsUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    operator fun invoke(student: List<StudentResponse.Student>): Flow<Unit> = flow {
        val listStudentEntity = student.map {
            StudentEntity(name = it.name, birth = it.birth)
        }
        studentRepository.insertListStudent(listStudentEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

}