package com.example.compose_clean_base.presentation.sam

import com.example.compose_clean_base.data.model.dto.CourseDto
import com.example.compose_clean_base.data.model.dto.StudentDto
import com.example.framework.base.StateObserver

data class SamState(
    var listCourse: List<CourseDto> = arrayListOf(),
    var listStudent: List<StudentDto> = arrayListOf(),
    var listStudentByCode: List<StudentDto> = arrayListOf(),

    var isCourseScreen: Boolean = true,
    var stateObserver: StateObserver<Nothing> = StateObserver.Empty
)

sealed class SamEvent {
    data object Back: SamEvent()

    data object InitData: SamEvent()

    data class RemoveStudent(val id: String) : SamEvent()
    data class RegisterStudent(val id: String) : SamEvent()

    data class SelectCourse(val id: String) : SamEvent()
}