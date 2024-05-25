package com.example.compose_clean_base.data.model.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    override val status: Int,
    override val message: String,
    ): BaseResponse()
