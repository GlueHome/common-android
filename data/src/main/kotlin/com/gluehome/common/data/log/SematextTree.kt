package com.gluehome.common.data.log

import android.util.Log
import com.sematext.logseneandroid.Logsene


class SematextTree(private val logsene: Logsene) : BaseSematextTree() {

    override fun i(message: String?, vararg args: Any?) {
        logsene.event(extractJsonObject(message, args))
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.DEBUG, Log.VERBOSE -> logsene.debug(message)
            Log.WARN -> logsene.warn(message)
            Log.ERROR -> logsene.error(t)
        }
    }
}
