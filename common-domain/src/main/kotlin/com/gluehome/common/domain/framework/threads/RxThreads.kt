package com.gluehome.common.domain.framework.threads

import io.reactivex.Scheduler

interface RxThreads {
    fun io(): Scheduler
    fun ui(): Scheduler
}
