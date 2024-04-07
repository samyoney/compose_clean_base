package com.example.compose_clean_base.data.local.dao

import androidx.room.Dao
import com.example.framework.room.dao.BaseDao
import com.example.compose_clean_base.data.model.local.StudentEntity

@Dao
interface StudentDao : BaseDao<StudentEntity>