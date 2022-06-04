package com.elnemr.core.domain.usecase.base

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<T, Params>(
    protected val stateFlow: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
) : UseCase<T, Params> {

    @Inject
    lateinit var repo: com.elnemr.core.domain.repository.IRepository

    init {
        stateFlow.distinctUntilChanged()
    }

    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default
    private val backgroundIODispatcher = Dispatchers.IO
    private val coroutineIOScope: CoroutineScope =
        CoroutineScope(backgroundIODispatcher + parentJob)

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    operator fun invoke(
        params: Params? = null
    ) {
        launch(backgroundIODispatcher) {
            execute(params)
        }
    }

    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> =
        coroutineIOScope.async { block() }

    fun clear() {
        parentJob.cancel()
    }

    override fun getStateFlow(): SharedFlow<T> = stateFlow

}
