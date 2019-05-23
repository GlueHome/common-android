package com.gluehome.common.rxthreads

import com.gluehome.common.threads.RxThreads
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidRxThreads : RxThreads {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}
