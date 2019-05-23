package com.gluehome.common.rxthreads

import com.gluehome.common.threads.RxThreads
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxThreads : RxThreads {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}
