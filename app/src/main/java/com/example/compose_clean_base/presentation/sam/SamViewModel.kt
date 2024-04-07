package com.example.compose_clean_base.presentation.sam

import com.example.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SamViewModel @Inject constructor(
) :
    BaseViewModel<SamState>() {
    override fun initialState() = SamState()
}