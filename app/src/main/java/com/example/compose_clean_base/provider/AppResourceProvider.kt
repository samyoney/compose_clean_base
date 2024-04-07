package com.example.compose_clean_base.provider

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.compose_clean_base.provider.mask.ResourceProvider

class AppResourceProvider(private val context: Context) : ResourceProvider {
    override fun getString(id: Int): String {
        return ContextCompat.getString(context, id)
    }

    override fun getDrawable(id: Int): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }
}