package com.gluehome.common.presentation.framework.threads

import com.gluehome.common.domain.framework.threads.CoroutineThreads
import kotlinx.coroutines.Dispatchers

class AndroidCoroutineThreads : CoroutineThreads {
    override fun ui() = Dispatchers.Main
    override fun io() = Dispatchers.IO
}
