package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.domain.framework.threads.RxThreads
import io.reactivex.Completable

abstract class CompletableUseCase<Type, in Params>(private val threads: RxThreads) where Type : Any {

    abstract fun run(params: Params): Completable

    open operator fun invoke(params: Params): Completable {
        return run(params)
                .subscribeOn(threads.io())
                .observeOn(threads.ui())
    }
}
