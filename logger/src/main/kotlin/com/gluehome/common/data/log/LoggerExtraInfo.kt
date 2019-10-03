package com.gluehome.common.data.log

class LoggerExtraInfo private constructor() {

    private object Holder {
        val INSTANCE = LoggerExtraInfo()
    }

    companion object {
        val instance: LoggerExtraInfo by lazy { Holder.INSTANCE }
    }

    private var extra = mutableMapOf<String, Any>()

    fun getAll() = extra

    fun add(key: String, value: Any) {
        extra.put(key, value)
    }

    fun remove(key: String) {
        extra.remove(key)
    }

    fun add(ext: Map<String, Any>) {
        ext.forEach { (k, v) -> add(k, v) }
    }

    fun clear() {
        extra = mutableMapOf()
    }
}
