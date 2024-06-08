package com.example.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framework.network.RequestState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<ViewState, ViewEvent> : ViewModel() {

    protected abstract fun initialState(): ViewState

    abstract fun onTriggerEvent(eventType: ViewEvent)

    open val uiState: MutableStateFlow<ViewState> by lazy {
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
        callFlow: Flow<RequestState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(requireNotNull(it.message)) }
            .collect { state ->
                when (state) {
                    is RequestState.Error -> handleError(requireNotNull(state.error.message))
                    is RequestState.Success -> completionHandler.invoke(state.result)
                }
            }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}