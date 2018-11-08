package com.gluehome.common.domain.logs


interface GlueLogger {
    fun d(message: String)
    fun w(message: String)
    fun e(message: String, exception: Exception)
    fun e(exception: Exception)
    fun e(message: String, vararg objs: Unit)
    fun e(exception: Throwable?)
}
