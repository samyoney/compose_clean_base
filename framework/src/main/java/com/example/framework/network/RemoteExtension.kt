package com.example.framework.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T : Any> safeFetchApi(call: suspend () -> T): RequestState<T> = withContext(Dispatchers.IO) {
    return@withContext try {
        val response = call()
        RequestState.Success(response)
    } catch (ex: Throwable) {
        RequestState.Error(ex.handleThrowable())
    }
}