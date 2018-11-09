package com.gluehome.common.domain.framework.interactor

import com.gluehome.common.domain.exceptions.Failure
import com.gluehome.common.domain.framework.functional.Either
import com.gluehome.common.domain.framework.threads.RxThreads
import io.reactivex.Single

abstract class SingleUseCase<Type, in Params>(private val threads: RxThreads) where Type : Any {

    abstract fun run(params: Params): Single<Either<Failure, Type>>

    open operator fun invoke(params: Params): Single<Either<Failure, Type>> {
        return run(params)
                .subscribeOn(threads.io())
                .observeOn(threads.ui())
    }
}
