package com.gluehome.common.presentation.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface.BOLD
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}


fun TextInputEditText.makeCopyOnClick(viewContainer: View, bgInt: Int) {
    this.setOnClickListener {
        showSnackAndCopy(viewContainer, this.string, bgInt)
    }
}

fun View.makeCopyOnClick(viewContainer: View, textToCopy: String, bgInt: Int) {
    this.setOnClickListener {
        showSnackAndCopy(viewContainer, textToCopy, bgInt)
    }
}

private fun View.showSnackAndCopy(
    viewContainer: View,
    textToCopy: String,
    bgInt: Int
) {
    Snackbar.make(viewContainer, "'$textToCopy' copied to the clipboard", Snackbar.LENGTH_SHORT).apply {
        config(viewContainer.context, bgInt)
        show()
    }

    context?.applicationContext?.copyToClipboard(textToCopy)
}

fun Context.copyToClipboard(text: CharSequence) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.primaryClip = clip
}

fun Snackbar.config(context: Context, bgDrawable: Int) {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(12, 12, 12, 12)
    this.view.layoutParams = params

    this.view.background = context.getDrawable(bgDrawable)

    ViewCompat.setElevation(this.view, 6f)
}


fun View.makeCustomTabWith(url: String) {
    this.setOnClickListener {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}

fun View.weight(value: Float) {
    val params = this.layoutParams as LinearLayout.LayoutParams
    params.weight = value
    this.layoutParams = params
}

fun Context.makeSpannable(template: Int, contentColor: Int, content: String = ""): SpannableString {
    return this.makeSpannable(this.getString(template, content), contentColor, content)
}

fun Context.makeSpannable(template: String, contentColor: Int, content: String = ""): SpannableString {
    val spannableString = SpannableString(template)

    val startIndex: Int
    val endIndex: Int

    if (content.isEmpty()) {
        startIndex = 0
        endIndex = spannableString.toString().length
    } else {
        startIndex = spannableString.toString().indexOf(content, 0, true)
        endIndex = startIndex + content.length
    }

    val foregroundColorSpan = ForegroundColorSpan(ContextCompat.getColor(this, contentColor))

    spannableString.setSpan(foregroundColorSpan, startIndex, endIndex, 0)
    spannableString.setSpan(StyleSpan(BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannableString
}
