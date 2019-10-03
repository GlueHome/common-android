package gluehome.common.presentation.framework.archcomponents

import androidx.lifecycle.ViewModel
import com.gluehome.common.domain.exceptions.Failure

open class BaseViewModel : ViewModel() {
    var failure = HybridLiveEvent<Failure>()

    fun handleFailure(failure: Failure) {
        this.failure.postValue(failure, false)
    }
}
