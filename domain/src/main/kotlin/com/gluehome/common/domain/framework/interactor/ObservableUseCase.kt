package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.domain.exceptions.Failure
import com.gluehome.common.domain.framework.functional.Either
import com.gluehome.common.threads.RxThreads
import io.reactivex.Observable

abstract class ObservableUseCase<Type, in Params>(private val threads: RxThreads) where Type : Any {

    lateinit var observable: Observable<Either<Failure, Type>>

    abstract fun run(params: Params): Observable<Either<Failure, Type>>

    open operator fun invoke(params: Params): Observable<Either<Failure, Type>> {
        return run(params)
            .subscribeOn(threads.executionThread())
            .observeOn(threads.postExecutionThread())
    }
}
