package com.example.compose_clean_base.data.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "status")
    val status: Int,
    @Json(name = "message")
    val message: String,
    @Json(name = "api_contents")
    val apiContents: T?
)