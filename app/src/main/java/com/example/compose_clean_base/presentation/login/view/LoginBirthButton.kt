package com.example.compose_clean_base.presentation.login.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.ExtraSmallSpacer
import com.example.compose_clean_base.app.theme.AppShapes
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.BaseTextColor
import com.example.compose_clean_base.app.theme.HintTextColor
import com.example.compose_clean_base.app.theme.LoginTextFieldBorder
import com.example.compose_clean_base.app.theme.TransparentColor
import com.example.compose_clean_base.app.theme.diaryInputFieldText
import com.example.compose_clean_base.app.theme.dimen10
import com.example.compose_clean_base.app.theme.dimen16
import com.example.compose_clean_base.app.theme.dimen20
import com.example.compose_clean_base.presentation.login.LoginState
import java.util.Calendar

@Composable
fun LoginBirthButton(
    uiState: LoginState,
    onYearMonthSelected: (year: Int, month: Int) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimen20(),
                horizontal = dimen16()
            )
    ) {
        Text(
            text = stringResource(R.string.birthday),
            style = AppTypography.body1
        )
        ExtraSmallSpacer()
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                showYearMonthPickerDialog(context, null) { year, month ->
                    onYearMonthSelected(year, month)
                }
            },
            shape = AppShapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = TransparentColor),
            border = LoginTextFieldBorder,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimen10()),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = uiState.birth.ifEmpty { stringResource(R.string.birthday_place_holder) },
                    style = if (uiState.birth.isEmpty()) AppTypography.subtitle1 else diaryInputFieldText,
                    color = if (uiState.birth.isEmpty()) HintTextColor else BaseTextColor
                )
            }
        }
        ExtraSmallSpacer()
        Text(
            text = stringResource(R.string.birthday_description),
            style = AppTypography.caption
        )
    }
}

fun showYearMonthPickerDialog(
    context: Context,
    parentView: ViewGroup?,
    onYearMonthSelected: (year: Int, month: Int) -> Unit
) {
    val dialogView = LayoutInflater.from(context).inflate(R.layout.year_month_picker, parentView)

    val yearPicker: NumberPicker = dialogView.findViewById(R.id.yearPicker)
    val monthPicker: NumberPicker = dialogView.findViewById(R.id.monthPicker)
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)

    yearPicker.minValue = currentYear - 100
    yearPicker.maxValue = currentYear
    yearPicker.value = currentYear
    monthPicker.minValue = 1
    monthPicker.maxValue = 12
    monthPicker.value = currentMonth + 1

    AlertDialog.Builder(context).apply {
        setView(dialogView)
        setPositiveButton(R.string.ok) { _, _ ->
            onYearMonthSelected(
                yearPicker.value,
                monthPicker.value
            )
        }
        setNegativeButton(
            R.string.cancel,
            null
        )
    }.create().show()
}