package com.example.compose_clean_base.data.model.local

import androidx.room.Embedded
import androidx.room.Relation

data class Enroll(
    @Embedded val course: CourseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val students: List<StudentEntity>)
