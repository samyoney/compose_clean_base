package com.example.compose_clean_base.data.usecase.login

import com.example.compose_clean_base.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveAccountInfoUseCase @Inject constructor(private val repository: AccountRepository) {

    operator fun invoke(
        username: String,
        password: String,
    ): Flow<Unit> = flow {
        repository.username = username
        repository.password = password
        emit(Unit)
    }.flowOn(Dispatchers.IO)
}