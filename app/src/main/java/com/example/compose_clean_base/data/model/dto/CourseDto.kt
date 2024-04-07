package com.example.compose_clean_base.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseDto(
    val name: String,
    val instructor: String,
    val topics: List<String>,
    val students: List<StudentDto>
) : Parcelable