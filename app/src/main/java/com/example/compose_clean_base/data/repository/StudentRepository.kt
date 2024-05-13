package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.remote.service.StudentService
import com.example.framework.extension.fromJson
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentService: StudentService,
    private val studentDao: StudentDao,
    private val cacheManager: CacheManager,
    ) {

    suspend fun fetchStudents() = if (BuildConfig.DEBUG) {
        requireNotNull((cacheManager.readFromAssets("student.json")).fromJson<StudentResponse>())
    } else {
        studentService.fetch()
    }

    suspend fun insertListStudent(studentEntity: List<StudentEntity>) = studentDao.insert(studentEntity)
}