package com.gluehome.common.data.log

import android.util.Log
import com.sematext.logseneandroid.Logsene
import org.json.JSONObject
import timber.log.Timber

class SematextTree(
    private val logsene: Logsene,
    private val loggerExtraInfo: LoggerExtraInfo
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        when (priority) {
            Log.DEBUG, Log.VERBOSE -> {
                val extra = loggerExtraInfo.getAll()
                if (extra.isNotEmpty()) {
                    logsene.event(enrichLog(message, extra))
                } else {
                    logsene.debug(message)
                }
            }
            Log.INFO -> logsene.info(message)
            Log.WARN -> logsene.warn(message)
            Log.ERROR -> {
                when (t == null) {
                    true -> logsene.error(message)
                    else -> logsene.error(t)
                }
            }
        }
    }

    private fun enrichLog(
        message: String,
        extraInfo: Map<String, Any>
    ): JSONObject {
        val fullInfo: HashMap<String, Any> = hashMapOf(
            "level" to "debug",
            "message" to message
        )

        extraInfo.forEach { fullInfo[it.key] = it.value }

        return JSONObject(fullInfo)
    }
}
