package com.gluehome.common.data.log

import android.util.Log
import com.sematext.logseneandroid.Logsene
import com.sematext.logseneandroid.Utils
import org.json.JSONObject
import timber.log.Timber

class SematextTree(
    private val logsene: Logsene,
    private val loggerExtraInfo: LoggerExtraInfo,
    private val shouldLogDebug: Boolean
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        if (!shouldLogDebug && priority == Log.DEBUG) {
            return
        }

        val fullInfo = mutableMapOf<String, Any>(
            "level" to mapPriorityToText(priority),
            "message" to message
        )
        if (priority == Log.ERROR && t != null) {

            fullInfo["exception"] = t.javaClass.toString()
            fullInfo["message"] = t.message ?: "empty error message"
            fullInfo["localized_message"] = t.localizedMessage ?: "empty localizedMessage"
            fullInfo["stacktrace"] = Utils.getStackTrace(t)
        }

        val extra = loggerExtraInfo.getAll()

        extra.forEach { fullInfo[it.key] = it.value }

        logsene.event(JSONObject(fullInfo as Map<*, *>))
    }

    private fun mapPriorityToText(priority: Int): String {
        return when (priority) {
            Log.INFO -> "info"
            Log.WARN -> "warn"
            Log.ERROR -> "error"
            else -> "debug"
        }
    }


}
