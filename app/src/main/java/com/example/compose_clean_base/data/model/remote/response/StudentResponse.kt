package com.example.compose_clean_base.data.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudentResponse(
    override val status: Int,
    override val message: String,
    @Json(name = "students")
    val students: List<Student>
    ): BaseResponse() {

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
