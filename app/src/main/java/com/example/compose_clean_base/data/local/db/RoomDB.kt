package com.example.compose_clean_base.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.model.local.*

@Database(
    entities = [CourseEntity::class, StudentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalConverter::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun courseDao(): CourseDao

    abstract fun studentDao(): StudentDao

}