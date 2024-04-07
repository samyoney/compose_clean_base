package com.example.compose_clean_base.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.framework.room.dao.BaseDao
import com.example.compose_clean_base.data.model.local.Enroll
import com.example.compose_clean_base.data.model.local.CourseEntity

@Dao
interface CourseDao : BaseDao<CourseEntity> {
    @Transaction
    @Query("SELECT * FROM CourseEntity")
    suspend fun getClassEnroll(): List<Enroll>

    @Query("SELECT * FROM CourseEntity WHERE id = :id")
    suspend fun getCourse(id: String): CourseEntity
}