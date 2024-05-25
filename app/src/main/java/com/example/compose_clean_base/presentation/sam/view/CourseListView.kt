package com.example.compose_clean_base.presentation.sam.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.dimen16
import com.example.compose_clean_base.app.theme.dimen8
import com.example.compose_clean_base.data.model.dto.CourseDto

@Composable
fun CourseListView(courses: List<CourseDto>, onClickItem : (id: String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(courses) { course ->
            CourseCard(course = course) {
                onClickItem(it)
            }
        }
    }
}

@Composable
fun CourseCard(course: CourseDto, onClickItem : (id: String) -> Unit) {
    Card(modifier = Modifier.padding(dimen8()).fillMaxWidth()) {
        Column(modifier = Modifier
            .padding(dimen16())
            .clickable {
                onClickItem(course.id)
            }
            ) {
            Text(text = course.name, style = AppTypography.h6)
            Text(text = "Instructor: ${course.instructor}")
            Text(text = "Topics:")
            course.topics.forEach { topic ->
                Text("- $topic")
            }
        }
    }
}