package com.gluehome.common.threads

import com.gluehome.common.threads.framework.threads.RxThreads
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxThreads : RxThreads {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}
