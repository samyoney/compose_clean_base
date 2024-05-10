package com.example.compose_clean_base.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")  val id: Long = 0,
    @Json(name = "course_id")  val courseId: String? = null,
    @Json(name = "name")  val name: String,
    @Json(name = "birth") val birth: String,
)
