package gluehome.common.presentation.framework

import com.gluehome.common.domain.exceptions.Failure

sealed class UIState {
    object Loading : UIState()
    object Empty : UIState()
    data class Problem(val error: Failure) : UIState()
    data class Success<T>(val data: T) : UIState()
}
