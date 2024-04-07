package com.example.framework.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T : Any> safeFetchApi(call: suspend () -> T): ApiState<T> = withContext(Dispatchers.IO) {
    return@withContext try {
        val response = call()
        ApiState.Success(response)
    } catch (ex: Throwable) {
        ApiState.Error(ex.handleThrowable())
    }
}