package com.example.compose_clean_base.data.usecase.login

import com.example.compose_clean_base.data.model.remote.response.LoginResponse
import com.example.compose_clean_base.data.repository.AccountRepository
import com.example.framework.network.RequestState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchLoginUseCase @Inject constructor(private val repository: AccountRepository) {

    operator fun invoke(username: String, password: String): Flow<RequestState<LoginResponse>> = flow {
        val result = safeFetchApi { repository.login(username, password) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}