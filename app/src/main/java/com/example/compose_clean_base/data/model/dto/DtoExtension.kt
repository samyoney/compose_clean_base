package com.example.compose_clean_base.data.model.dto

import com.example.compose_clean_base.data.model.local.CourseEntity
import com.example.compose_clean_base.data.model.local.StudentEntity
import com.example.compose_clean_base.data.model.local.EnrollEntity
import com.example.compose_clean_base.data.model.remote.response.CourseResponse

fun EnrollEntity.toDto() = CourseDto(
    id = course.id,
    name = course.name,
    instructor = course.instructor,
    topics = course.topics,
    students = students.toDtoList()
)

fun CourseResponse.toCourseDtoList() = course.map { course -> CourseDto(
    id = course.id,
    name = course.name,
    instructor = course.instructor,
    topics = course.topics,
    students = course.students.map { student -> StudentDto(
        id = student.id.toLong(),
        name = student.name,
        birth = student.birth
    ) })
}

fun CourseDto.toEntity() = CourseEntity(
    id = id,
    name = name,
    instructor = instructor,
    topics = topics
)

fun List<StudentDto>.toEntityList() = map { it.toEntity() }

fun List<StudentEntity>.toDtoList() = map { it.toDto() }

private fun StudentDto.toEntity() = StudentEntity(
    id = id,
    name = name,
    birth = birth,
)

private fun StudentEntity.toDto() = StudentDto(
    id = id,
    name = name,
    birth = birth,
)