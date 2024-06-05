package com.example.compose_clean_base.data.repository

import com.example.compose_clean_base.data.local.dao.StudentDao
import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.remote.service.StudentService
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentService: StudentService,
    private val studentDao: StudentDao,
    ) {

    suspend fun fetchStudents() = studentService.fetch()

    suspend fun getStudent(id:String) = studentDao.getStudent(id)

    suspend fun getListStudent() = studentDao.getListStudent()

    suspend fun updateStudent(studentEntity: StudentEntity) = studentDao.update(studentEntity)

    suspend fun insertListStudent(studentEntities: List<StudentEntity>) = studentDao.insert(studentEntities)
}