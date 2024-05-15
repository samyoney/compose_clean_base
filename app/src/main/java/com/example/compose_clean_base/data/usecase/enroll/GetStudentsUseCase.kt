package com.example.compose_clean_base.data.usecase.enroll

import com.example.compose_clean_base.data.model.dto.StudentDto
import com.example.compose_clean_base.data.model.dto.toDto
import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetStudentsUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    operator fun invoke(): Flow<List<StudentDto>> = flow {
        emit(studentRepository.getListStudent().map {
            it.toDto()
        })
    }.flowOn(Dispatchers.IO)
}
