package com.example.compose_clean_base.data.usecase.login

import com.example.compose_clean_base.data.model.remote.response.RegisterResponse
import com.example.compose_clean_base.data.repository.AccountRepository
import com.example.framework.network.RequestState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchRegisterUseCase @Inject constructor(private val repository: AccountRepository) {

    operator fun invoke(
        username: String,
        password: String,
        courseId: String,
        name: String,
        birth: String
    ): Flow<RequestState<RegisterResponse>> = flow {
        val result = safeFetchApi { repository.register(username, password, courseId, name, birth) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}