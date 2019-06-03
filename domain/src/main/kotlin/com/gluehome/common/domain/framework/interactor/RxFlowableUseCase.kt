package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.threads.RxThreads
import io.reactivex.Flowable

abstract class RxFlowableUseCase<Type, in Params>(private val threads: RxThreads) where Type : Any {

    abstract fun run(params: Params): Flowable<Type>

    open operator fun invoke(params: Params): Flowable<Type> {
        return run(params)
            .subscribeOn(threads.executionThread())
            .observeOn(threads.postExecutionThread())
    }
}