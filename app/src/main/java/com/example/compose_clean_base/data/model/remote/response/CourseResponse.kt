package com.example.compose_clean_base.data.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    override val status: Int,
    override val message: String,
    @Json(name = "courses")
    val course: List<Course>
    ): BaseResponse() {

    @JsonClass(generateAdapter = true)
    data class Course(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "instructor")
        val instructor: String,
        @Json(name = "topics")
        val topics: List<String>,
        @Json(name = "students")
        val students: List<Student>
    )

    @JsonClass(generateAdapter = true)
    data class Student(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "birth")
        val birth: String,
    )
}
