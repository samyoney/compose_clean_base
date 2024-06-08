package com.example.compose_clean_base.presentation.sam

import com.example.compose_clean_base.data.usecase.enroll.AddStudentIntoCourseUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetCoursesUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetStudentsByCourseIdUseCase
import com.example.compose_clean_base.data.usecase.enroll.GetStudentsUseCase
import com.example.compose_clean_base.data.usecase.enroll.RemoveStudentFromCourseUseCase
import com.example.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SamViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val getStudentsByCourseIdUseCase: GetStudentsByCourseIdUseCase,
    private val addStudentIntoCourseUseCase: AddStudentIntoCourseUseCase,
    private val removeStudentFromCourseUseCase: RemoveStudentFromCourseUseCase
) : BaseViewModel<SamState, SamEvent>() {
    override fun initialState() = SamState()
    private var currentCourseId = String()

    override fun onTriggerEvent(eventType: SamEvent) {
        when (eventType) {
            is SamEvent.InitData -> {
                onInitData()
            }

            is SamEvent.Back -> {
                onBack()
            }

            is SamEvent.RegisterStudent -> {
                onRegisterStudent(eventType.id)
            }

            is SamEvent.RemoveStudent -> {
                onRemoveStudent(eventType.id)
            }

            is SamEvent.SelectCourse -> {
                onSelectCourse(eventType.id)
            }
        }
    }

    private fun onInitData() = safeLaunch {
        executeLocalUseCase(getCoursesUseCase()) { data ->
            uiState.update { it.copy(listCourse = data) }
        }
        executeLocalUseCase(getStudentsUseCase()) { data ->
            uiState.update { it.copy(listStudent = data) }
        }
    }

    private fun onBack() = safeLaunch {
        uiState.update { it.copy(isCourseScreen = true) }
    }


    private fun onRegisterStudent(id: String) = safeLaunch {
        executeLocalUseCase(addStudentIntoCourseUseCase(id, currentCourseId)) {
            onSelectCourse(currentCourseId)
        }
    }

    private fun onRemoveStudent(id: String) = safeLaunch {
        executeLocalUseCase(removeStudentFromCourseUseCase(id)) {
            onSelectCourse(currentCourseId)
        }
    }

    private fun onSelectCourse(courseId: String) = safeLaunch {
        currentCourseId = courseId
        executeLocalUseCase(getStudentsByCourseIdUseCase(courseId)) { data ->
            uiState.update { it.copy(isCourseScreen = false, listStudentByCode = data) }
        }
    }
}