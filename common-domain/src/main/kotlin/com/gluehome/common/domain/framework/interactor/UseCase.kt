package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.domain.exceptions.Failure
import com.gluehome.common.domain.framework.functional.Either
import com.gluehome.common.domain.framework.threads.CoroutineThreads
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params>(private val threads: CoroutineThreads) where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = CoroutineScope(threads.io()).async { run(params) }
        CoroutineScope(threads.ui()).launch { onResult(job.await()) }
    }
}


