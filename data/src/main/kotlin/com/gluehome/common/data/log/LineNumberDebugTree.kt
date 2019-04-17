package com.gluehome.common.data.log

import timber.log.Timber

class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        String.format("%s:%s",
            element.lineNumber,
            super.createStackElementTag(element)
        )
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
