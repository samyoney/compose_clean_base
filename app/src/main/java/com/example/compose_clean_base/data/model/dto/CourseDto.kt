package com.example.compose_clean_base.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseDto(
    val id: String,
    val name: String,
    val instructor: String,
    val topics: List<String>
) : Parcelable