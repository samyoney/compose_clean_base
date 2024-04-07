package com.example.compose_clean_base.presentation.sam

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.ExtraLargeSpacer
import com.example.compose_clean_base.app.component.LargeSpacer
import com.example.compose_clean_base.app.component.Toolbar
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.dimen24
import com.example.compose_clean_base.app.theme.dimen8
import com.example.compose_clean_base.presentation.sam.view.CourseListView
import com.example.compose_clean_base.presentation.sam.view.StudentListView
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.asStateFlow

@Destination
@Composable
fun SamScreen(
    viewModel: SamViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    viewModel.onTriggerEvent(SamEvent.InitData)

    BackHandler {
        viewModel.onTriggerEvent(SamEvent.Back)
    }

    Scaffold(topBar = { Toolbar(if (uiState.isCourseScreen) R.string.course_nav_tab else R.string.student_nav_tab) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(
                    horizontal = dimen24()
                )
            ) {
                if (uiState.isCourseScreen) {
                    CourseListView(uiState.listCourse) {
                        viewModel.onTriggerEvent(SamEvent.SelectCourse(it))
                    }
                } else {
                    ExtraLargeSpacer()
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = dimen8()
                            )
                            .weight(1.0F)
                    ) {
                        Text(text = stringResource(id = R.string.total_students_title), style = AppTypography.h6)
                        LargeSpacer()
                        StudentListView(isRegistered = false, students = uiState.listStudent) {
                            viewModel.onTriggerEvent(SamEvent.RegisterStudent(it.toString()))
                        }
                    }
                    ExtraLargeSpacer()
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = dimen8()
                            )
                            .weight(1.0F)
                    ) {
                        Text(text = stringResource(id = R.string.enrolled_student), style = AppTypography.h6)
                        LargeSpacer()
                        StudentListView(isRegistered = true, students = uiState.listStudentByCode) {
                            viewModel.onTriggerEvent(SamEvent.RemoveStudent(it.toString()))
                        }
                    }
                }
            }
        })
}





