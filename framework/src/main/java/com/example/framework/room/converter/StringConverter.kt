package com.example.framework.room.converter

import androidx.room.TypeConverter
import com.example.framework.extension.fromJson
import com.example.framework.extension.toJson

open class StringConverter {
    @TypeConverter
    fun toListOfString(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfString(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}