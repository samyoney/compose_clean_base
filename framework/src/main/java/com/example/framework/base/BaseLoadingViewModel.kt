package com.example.framework.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseLoadingViewModel<ViewState, ViewEvent> : BaseViewModel<LoadingState<ViewState>, ViewEvent>() {

    abstract fun initialLoadingState(): ViewState
    override fun initialState() = LoadingState.Idle

    private lateinit var loadedStateData: LoadingState.Loaded<ViewState>

    fun MutableStateFlow<LoadingState<ViewState>>.updateLoaded(
        transform: (ViewState) -> ViewState
    ) {
        if (!::loadedStateData.isInitialized) {
            loadedStateData = LoadingState.Loaded(initialLoadingState())
        }
        this.update {
            if (loadedStateData.data != null) {
                val newLoadStateData = transform(
                    requireNotNull(loadedStateData.data)
                )
                loadedStateData.data = newLoadStateData
                loadedStateData
            } else LoadingState.Loaded()
        }
    }
}