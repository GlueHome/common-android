package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.threads.RxThreads
import io.reactivex.Single

abstract class SingleUseCase<Type, in Params>(
    private val threads: RxThreads
) where Type : Any {

    abstract fun run(params: Params): Single<Type>

    open operator fun invoke(params: Params): Single<Type> {
        return run(params)
            .subscribeOn(threads.io())
            .observeOn(threads.ui())
    }
}
