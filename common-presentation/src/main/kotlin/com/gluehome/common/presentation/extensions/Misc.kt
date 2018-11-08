package com.gluehome.common.presentation.extensions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun androidx.fragment.app.Fragment.hideKeyboard() {
    activity!!.hideKeyboard(view!!)
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Float.toDP(context: Context): Int {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this@toDP,
            context.resources.displayMetrics
    ).toInt()
}

fun Bundle.add(key: String, value: Any) {
    when (value) {
        is Int -> this.putInt(key, value)
        is Long -> this.putLong(key, value)
        is CharSequence -> this.putCharSequence(key, value)
        is Char -> this.putChar(key, value)
        is String -> this.putString(key, value)
        is Float -> this.putFloat(key, value)
        is Double -> this.putDouble(key, value)
        is Short -> this.putShort(key, value)
        is Boolean -> this.putBoolean(key, value)
    }
}
