package com.gluehome.common.domain

import com.gluehome.common.domain.framework.Observable
import kotlin.test.assertEquals

fun <T> verifyStateChanges(states: List<T>, observable: Observable<T>, `when`: () -> Unit) {
    var count = 0
    observable.observe { state, _ ->
        assertEquals(states[count], state)
        count++
    }

    `when`()

    assertEquals(states.size, count)
}
