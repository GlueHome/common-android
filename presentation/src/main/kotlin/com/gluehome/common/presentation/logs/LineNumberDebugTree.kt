package com.gluehome.common.presentation.logs

import timber.log.Timber

class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        String.format("%s:%s",
            element.getLineNumber(),
            super.createStackElementTag(element)
        );
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
