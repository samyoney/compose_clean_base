package com.example.compose_clean_base.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentDto(
    val id: Long,
    val courseId: String?,
    val name: String,
    val birth: String,
): Parcelable
