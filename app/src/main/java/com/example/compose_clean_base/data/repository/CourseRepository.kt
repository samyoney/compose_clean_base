package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.local.dao.CourseDao
import com.example.compose_clean_base.data.model.local.CourseEntity
import com.example.compose_clean_base.data.model.remote.response.CourseResponse
import com.example.compose_clean_base.data.remote.service.CourseService
import com.example.framework.extension.fromJson
import com.example.framework.pref.CacheManager
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val service: CourseService,
    private val courseDao: CourseDao,
    private val cacheManager: CacheManager
) {
    suspend fun fetchCourses() = if (BuildConfig.DEBUG) {
        requireNotNull((cacheManager.readFromAssets("course.json")).fromJson<CourseResponse>())
    } else {
        service.fetch()
    }

    suspend fun insertListCourse(courseEntity: List<CourseEntity>) = courseDao.insert(courseEntity)

    suspend fun getEnrollCourse() = courseDao.getClassEnroll()
}