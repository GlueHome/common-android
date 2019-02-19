package com.gluehome.common.data.log

import timber.log.Timber

class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "[${super.createStackElementTag(element)}.${element.methodName} @ line:${element.lineNumber}] "
    }
}
