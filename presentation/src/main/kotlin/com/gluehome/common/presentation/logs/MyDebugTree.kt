package com.gluehome.common.presentation.logs

import timber.log.Timber

class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "[${super.createStackElementTag(element)}.${element.methodName} @ line:${element.lineNumber}] "
    }
}
