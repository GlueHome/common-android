package com.gluehome.common.data.log

import org.json.JSONObject
import timber.log.Timber

open class BaseSematextTree : Timber.DebugTree() {

    internal fun extractJsonObject(message: String?, args: Array<out Any?>): JSONObject {
        val extraInfo = JSONObject()

        extraInfo.put("level", "info")
        extraInfo.put("message", extractMessage(message, args))

        if (args.size > 1 && args[1] is Map<*, *>) {
            val map = args[1] as Map<String, Any?>
            map.forEach { extraInfo.put(it.key, it.value) }
        }

        return extraInfo
    }

    private fun extractMessage(message: String?, args: Array<out Any?>): String? =
        if (args.isEmpty()) message else args[0] as String

}