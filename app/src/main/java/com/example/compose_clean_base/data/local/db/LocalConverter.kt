package com.example.compose_clean_base.data.local.db

import androidx.room.TypeConverter
import com.example.framework.extension.fromJson
import com.example.framework.extension.toJson
import com.example.framework.room.converter.StringConverter
import com.example.compose_clean_base.data.model.local.StudentEntity

class LocalConverter: StringConverter() {
    @TypeConverter
    fun toConvertStringToList(stringValue: String): List<StudentEntity>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun toConvertListToString(listOfString: List<StudentEntity>?): String {
        return listOfString.toJson()
    }
}