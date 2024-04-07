package com.example.compose_clean_base.data.model.dto

import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.model.local.EnrollEntity

fun EnrollEntity.toDto() = CourseDto(
    name = course.name,
    instructor = course.instructor,
    topics = course.topics,
    students = students.toDtoList()
)

private fun List<StudentEntity>.toDtoList() = map { it.toDto() }

private fun StudentEntity.toDto() = StudentDto(
    id = id.toString(),
    name = name,
    birth = birth,
)