package com.example.framework.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.framework.extension.fromJson
import com.example.framework.extension.fromJsonList
import com.example.framework.extension.getDefaultSharedPrefName
import com.example.framework.extension.getPrefs
import com.example.framework.extension.toJson

class CacheManager(
    val context: Context,
    prefFileName: String? = null
) {

    private val prefs: SharedPreferences = context.getPrefs(
        prefFileName ?: context.getDefaultSharedPrefName()
    )

    @Suppress("UNCHECKED_CAST")
    fun <T> read(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> prefs.getString(key, defaultValue as String) as T ?: defaultValue
            is Int -> prefs.getInt(key, defaultValue as Int) as T ?: defaultValue
            is Boolean -> prefs.getBoolean(key, defaultValue as Boolean) as T ?: defaultValue
            is Long -> prefs.getLong(key, defaultValue as Long) as T ?: defaultValue
            else -> defaultValue
        }
    }

    fun <T> write(key: String, value: T) {
        when (value) {
            is String -> prefs.edit { putString(key, value).apply() }
            is Int -> prefs.edit { putInt(key, value).apply() }
            is Boolean -> prefs.edit { putBoolean(key, value).apply() }
            is Long -> prefs.edit { putLong(key, value).apply() }
            else -> Unit
        }
    }

    fun clear(key: String): Unit = prefs.edit {
        remove(key)
    }

    fun clearEverything(callBack: () -> Unit = {}) {
        prefs.edit {
            clear().commit()
            callBack.invoke()
        }
    }

    inline fun <reified T> readObject(key: String): T? {
        val readValue = read(key, "")
        return if (readValue.isEmpty()) {
            null
        } else {
            readValue.fromJson()
        }
    }

    fun writeObject(key: String, value: Any) {
        write(key, value.toJson())
    }

    inline fun <reified T> readListObject(key: String): List<T>? {
        return try {
            val value = read(key, "")
            if (value.isEmpty()) {
                null
            } else {
                value.fromJsonList<T>()
            }
        } catch (ex: Exception) {
            null
        }
    }

    inline fun readFromAssets(uri: String): String {
        return context.assets.open(uri).bufferedReader().use { it.readText() }
    }
}