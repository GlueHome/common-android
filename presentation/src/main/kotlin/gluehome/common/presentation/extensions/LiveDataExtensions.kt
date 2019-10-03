package gluehome.common.presentation.extensions

import com.gluehome.common.domain.framework.Observable
import gluehome.common.presentation.framework.archcomponents.HybridLiveEvent

fun <T> HybridLiveEvent<T>.observe(observable: Observable<T>) {
    observable.observe { value, sticky ->
        this.postValue(value, sticky)
    }
}

fun <T> liveDataObserving(observable: Observable<T>): HybridLiveEvent<T> {
    return HybridLiveEvent<T>().apply {
        observe(observable)
    }
}
