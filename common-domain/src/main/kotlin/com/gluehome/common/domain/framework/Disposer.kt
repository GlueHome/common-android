package com.gluehome.common.domain.framework

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class Disposer private constructor() {

    private object Holder {
        val INSTANCE = Disposer()
    }

    companion object {
        val instance: Disposer by lazy { Holder.INSTANCE }
    }

    private val compositeDisposable = CompositeDisposable()

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}
