package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveAccountInfoUseCase @Inject constructor(private val repository: StudentRepository) {

    operator fun invoke(
        username: String,
        password: String,
    ): Flow<Unit> = flow<Unit> {
        repository.username = username
        repository.password = password
    }.flowOn(Dispatchers.IO)
}