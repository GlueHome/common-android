package com.gluehome.common.threads

import io.reactivex.Scheduler

interface RxThreads {
    fun executionThread(): Scheduler
    fun postExecutionThread(): Scheduler
}
