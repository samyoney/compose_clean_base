package com.example.compose_clean_base.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.framework.room.dao.BaseDao
import com.example.compose_clean_base.data.model.local.StudentEntity

@Dao
interface StudentDao : BaseDao<StudentEntity> {
    @Query("SELECT * FROM StudentEntity")
    suspend fun getListStudent(): List<StudentEntity>


    @Query("SELECT * FROM StudentEntity WHERE id = :id")
    suspend fun getStudent(id: String): StudentEntity
}