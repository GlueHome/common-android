package com.gluehome.common.data.log

import android.annotation.SuppressLint
import android.util.Log

class DebugSematextTree(private val tag: String) : BaseSematextTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "[${super.createStackElementTag(element)}.${element.methodName} @ line:${element.lineNumber}] "
    }

    @SuppressLint("LogNotTimber")
    override fun i(message: String?, vararg args: Any?) {
        Log.i(tag, extractJsonObject(message, args).toString())
    }

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.DEBUG, Log.VERBOSE -> Log.d(tag, message)
            Log.WARN -> Log.w(tag, message)
            Log.ERROR -> Log.e(tag, t.toString())
        }
    }
}