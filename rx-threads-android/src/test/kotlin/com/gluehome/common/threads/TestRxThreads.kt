package com.gluehome.common.threads

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxThreads : RxThreads {
    override fun executionThread(): Scheduler = Schedulers.trampoline()
    override fun postExecutionThread(): Scheduler = Schedulers.trampoline()
}
