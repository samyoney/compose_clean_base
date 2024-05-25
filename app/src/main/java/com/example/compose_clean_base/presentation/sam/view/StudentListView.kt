package com.example.compose_clean_base.presentation.sam.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.theme.AppShapes
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.BaseColor
import com.example.compose_clean_base.app.theme.PurpleBgColor
import com.example.compose_clean_base.app.theme.dimen4
import com.example.compose_clean_base.app.theme.dimen54
import com.example.compose_clean_base.app.theme.dimen8
import com.example.compose_clean_base.data.model.dto.StudentDto

@Composable
fun StudentListView(isRegistered: Boolean, students: List<StudentDto>, onClick: (id: Long) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(students) { studentDto ->
            StudentCard(isRegistered, student = studentDto, onClick)
        }
    }
}

@Composable
fun StudentCard(isRegistered: Boolean, student: StudentDto, onClick: (id: Long) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = dimen8())
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxHeight()) {
            Text(
                modifier = Modifier
                    .padding(dimen8())
                    .weight(1.0F)
                    .align(Alignment.CenterVertically),
                text = "${student.name} (${student.birth})"
            )
            Button(
                onClick = { onClick(student.id) }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (isRegistered) Color.Red else PurpleBgColor, contentColor = BaseColor
                ), modifier = Modifier
                    .height(dimen54())
                    .padding(dimen4())
                    .clip(AppShapes.small)
            ) {
                Text(
                    stringResource(id = if (isRegistered) R.string.remove_student else R.string.register_course),
                    style = AppTypography.subtitle1.copy(color = BaseColor)
                )
            }
        }
    }
}