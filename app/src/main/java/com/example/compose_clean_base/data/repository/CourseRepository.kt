package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.model.local.CourseEntity
import com.example.compose_clean_base.data.remote.service.CourseService
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val service: CourseService,
    private val courseDao: CourseDao,
) {
    suspend fun fetchCourses() = service.fetch()

    suspend fun insertListCourse(courseEntities: List<CourseEntity>) = courseDao.insert(courseEntities)

    suspend fun getEnrollCourse() = courseDao.getClassEnroll()
}