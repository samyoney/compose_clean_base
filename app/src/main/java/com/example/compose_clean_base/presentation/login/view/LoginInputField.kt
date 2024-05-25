package com.example.compose_clean_base.presentation.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.compose_clean_base.app.component.ExtraSmallSpacer
import com.example.compose_clean_base.app.theme.AppShapes
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.BaseTextColor
import com.example.compose_clean_base.app.theme.HintTextColor
import com.example.compose_clean_base.app.theme.LoginTextFieldBorder
import com.example.compose_clean_base.app.theme.TransparentColor
import com.example.compose_clean_base.app.theme.diaryInputFieldText
import com.example.compose_clean_base.app.theme.dimen16

@Composable
fun LoginInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    helperText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimen16())
    ) {
        Text(
            text = label,
            style = AppTypography.body1
        )
        ExtraSmallSpacer()
        TextField(
            value = value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                capitalization = KeyboardCapitalization.Characters
            ),
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(TransparentColor)
                .border(
                    LoginTextFieldBorder,
                    shape = AppShapes.medium
                ),
            singleLine = true,
            textStyle = diaryInputFieldText.copy(color = BaseTextColor),
            placeholder = {
                Text(
                    text = placeholder,
                    style = diaryInputFieldText,
                    color = HintTextColor
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TransparentColor,
                unfocusedIndicatorColor = TransparentColor,
                focusedIndicatorColor = TransparentColor
            ),
        )
        ExtraSmallSpacer()
        Text(
            text = helperText,
            style = AppTypography.caption
        )
    }
}