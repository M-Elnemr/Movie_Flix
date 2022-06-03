package com.elnemr.movieflix.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged

abstract class BaseViewModel<T> : ViewModel() {

    // config MutableSharedFlow to act exactly like MutableStateFlow
    // as we don't want to provide init value
    protected val mediator: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        mediator.distinctUntilChanged()
    }

    fun getStateFlow() = mediator
}