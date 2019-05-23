package com.gluehome.common.threads

import io.reactivex.Scheduler

interface RxThreads {
    fun io(): Scheduler
    fun ui(): Scheduler
}
