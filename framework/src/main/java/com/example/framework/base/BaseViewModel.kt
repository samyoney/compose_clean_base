package com.example.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framework.network.ApiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<ViewState: BaseViewState<*>> : ViewModel() {

    abstract fun initialState(): ViewState

    val uiState: MutableStateFlow<ViewState> by lazy {
        MutableStateFlow(initialState())
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(SAFE_LAUNCH_EXCEPTION).e(exception)
        handleError(requireNotNull(exception.message))
    }

    open fun handleError(errorText: String) {}

    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    protected suspend fun <T> executeLocalUseCase(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(requireNotNull(it.message)) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> executeRemoteUseCase(
        callFlow: Flow<ApiState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(requireNotNull(it.message)) }
            .collect { state ->
                when (state) {
                    is ApiState.Error -> handleError(requireNotNull(state.error.message))
                    is ApiState.Success -> completionHandler.invoke(state.result)
                }
            }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}