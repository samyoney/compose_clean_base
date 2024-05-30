package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.BuildConfig
import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.framework.pref.CacheManager
import com.example.compose_clean_base.data.model.remote.response.StudentResponse
import com.example.compose_clean_base.data.remote.service.StudentService
import com.example.framework.extension.fromJson
import kotlinx.coroutines.delay
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentService: StudentService,
    private val studentDao: StudentDao,
    private val cacheManager: CacheManager,
    ) {

    suspend fun fetchStudents() = if (BuildConfig.DEBUG) {
        delay(1000)
        requireNotNull((cacheManager.readFromAssets("student.json")).fromJson<StudentResponse>())
    } else {
        studentService.fetch()
    }

    suspend fun getStudent(id:String) = studentDao.getStudent(id)

    suspend fun getListStudent() = studentDao.getListStudent()

    suspend fun updateStudent(studentEntity: StudentEntity) = studentDao.update(studentEntity)

    suspend fun insertListStudent(studentEntities: List<StudentEntity>) = studentDao.insert(studentEntities)
}