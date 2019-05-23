package com.gluehome.common.presentation.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.alertDialog(
    title: String,
    message: String,
    positiveMessage: String,
    positiveAction: () -> Unit = {},
    negativeMessage: String = "",
    negativeAction: () -> Unit = {}
) {
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(positiveMessage) { _, _ -> positiveAction() }
        setNegativeButton(negativeMessage) { _, _ -> negativeAction() }
        create()
        show()
    }
}
