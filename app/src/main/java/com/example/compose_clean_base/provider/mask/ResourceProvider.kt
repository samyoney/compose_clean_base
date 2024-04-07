package com.example.compose_clean_base.provider.mask

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String

    fun getDrawable(@DrawableRes id: Int): Drawable?
}