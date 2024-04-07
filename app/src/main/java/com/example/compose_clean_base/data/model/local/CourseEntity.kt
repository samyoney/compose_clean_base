package com.example.compose_clean_base.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val instructor: String,
    val topics: List<String>,
    )