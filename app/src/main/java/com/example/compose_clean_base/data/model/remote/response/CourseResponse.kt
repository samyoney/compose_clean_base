package com.example.compose_clean_base.data.model.remote.response


import com.example.compose_clean_base.data.model.dto.StudentDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "instructor")
    val instructor: String,
    @Json(name = "topics")
    val topics: List<String>,
    @Json(name = "students")
    val students: List<StudentDto>
)
