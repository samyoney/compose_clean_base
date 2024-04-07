package com.example.compose_clean_base.data.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "course_id") val courseId: String,
    @Json(name = "name") val name: String,
    @Json(name = "birth") val birth: String,
)