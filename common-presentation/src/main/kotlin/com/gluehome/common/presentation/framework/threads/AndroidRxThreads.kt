package com.gluehome.common.presentation.framework.threads

import com.gluehome.common.domain.framework.threads.RxThreads
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidRxThreads : RxThreads {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}
