package com.example.compose_clean_base.data.usecase

import com.example.compose_clean_base.data.model.remote.response.LoginResponse
import com.example.compose_clean_base.data.repository.AccountRepository
import com.example.framework.network.ApiState
import com.example.framework.network.safeFetchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchAutoLoginUseCase @Inject constructor(private val repository: AccountRepository) {

    operator fun invoke(
    ): Flow<ApiState<LoginResponse>> = flow {
        val result = safeFetchApi { repository.login(repository.username, repository.password) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}