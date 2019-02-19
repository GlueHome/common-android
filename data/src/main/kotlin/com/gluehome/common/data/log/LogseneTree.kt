package com.gluehome.common.data.log

import android.util.Log
import com.sematext.android.Logsene
import timber.log.Timber

class LogseneTree(private val logsene: Logsene) : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.DEBUG, Log.VERBOSE -> logsene.debug(message)
            Log.WARN -> logsene.warn(message)
            Log.ERROR -> logsene.error(t)
        }
    }
}