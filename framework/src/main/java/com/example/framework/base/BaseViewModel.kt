package com.example.framework.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framework.network.ApiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<ViewState : Any> : ViewModel() {

    val _uiState: MutableStateFlow<StateObserver<ViewState>> by lazy {
        val data = initialState()
        uiState = data
        MutableStateFlow(StateObserver.Initial(data = data))
    }

    abstract fun initialState(): ViewState

    protected lateinit var uiState: ViewState

    fun updateState(transform: (ViewState) -> ViewState) {
        _uiState.update {
            val transformData = transform(uiState)
            val state = StateObserver.Update(data = transformData)
            uiState = state.data
            state
        }
    }

    fun updateObservableState(transform: (StateObserver<ViewState>) -> StateObserver<ViewState>) {
        _uiState.update { state ->
            transform(state)
        }
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