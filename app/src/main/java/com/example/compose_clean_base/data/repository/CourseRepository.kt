package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.model.local.CourseEntity
import com.example.compose_clean_base.data.remote.service.CourseService
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val service: CourseService,
    private val courseDao: CourseDao,
) {
    suspend fun getCoursesData() = service.fetch()

    suspend fun saveCourse(entity: CourseEntity) {
        courseDao.insert(entity)
    }
}