package com.gluehome.common.data.log


class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "[${super.createStackElementTag(element)}.${element.methodName} @ line:${element.lineNumber}] "
    }
}
