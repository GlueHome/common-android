package com.gluehome.common.domain.framework

class Observable<Type> {

    private var callback: ((Type, Boolean) -> Unit)? = null

    fun postValue(value: Type, sticky: Boolean = true) {
        callback?.invoke(value, sticky)
    }

    fun postNonStickyValue(value: Type) {
        callback?.invoke(value, false)
    }

    fun observe(callback: (Type, Boolean) -> Unit) {
        this.callback = callback
    }
}
