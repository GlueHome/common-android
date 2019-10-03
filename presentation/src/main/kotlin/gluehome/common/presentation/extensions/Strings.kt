package gluehome.common.presentation.extensions

import android.util.Patterns

fun String.Companion.empty() = ""

fun String?.or(orString: String): String = this ?: orString

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
