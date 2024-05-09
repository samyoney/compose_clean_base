package com.example.compose_clean_base.data.model.remote.response


import com.squareup.moshi.Json

abstract class BaseResponse {
    @Json(name = "status")
    abstract val status: Int
    @Json(name = "message")
    abstract val message: String
}