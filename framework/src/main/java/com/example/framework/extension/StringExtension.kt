package com.example.framework.extension

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Size

const val EMPTY = ""

fun String?.safe(): String {
    return this ?: EMPTY
}

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

@Suppress("DEPRECATION")
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.toSize(sizeString: String): Size? {
    val parts = sizeString.split("x")
    if (parts.size == 2) {
        try {
            val width = parts[0].toInt()
            val height = parts[1].toInt()
            return Size(width, height)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }
    return null
}
