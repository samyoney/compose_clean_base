package com.example.compose_clean_base.presentation.login

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.ErrorDialog
import com.example.compose_clean_base.app.component.ExtraLargeSpacer
import com.example.compose_clean_base.app.component.ExtraSmallSpacer
import com.example.compose_clean_base.app.component.FullScreenLoading
import com.example.compose_clean_base.app.component.Toolbar
import com.example.compose_clean_base.app.theme.BaseTextColor
import com.example.compose_clean_base.app.theme.PurpleBgColor
import com.example.compose_clean_base.app.theme.BaseColor
import com.example.compose_clean_base.app.theme.HintTextColor
import com.example.compose_clean_base.app.theme.AppShapes
import com.example.compose_clean_base.app.theme.AppTypography
import com.example.compose_clean_base.app.theme.TransparentColor
import com.example.compose_clean_base.app.theme.diaryInputFieldText
import com.example.compose_clean_base.app.theme.dimen10
import com.example.compose_clean_base.app.theme.dimen16
import com.example.compose_clean_base.app.theme.dimen20
import com.example.compose_clean_base.app.theme.dimen38
import com.example.compose_clean_base.app.theme.dimen54
import com.example.compose_clean_base.app.theme.LoginTextFieldBorder
import com.example.compose_clean_base.provider.mask.NavigationProvider
import com.example.framework.base.CommonState
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { Toolbar(R.string.login_nav_tab) },
        content = { padding ->
            val modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .clickable(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
            Column(modifier = modifier) {
                LoginInputField(
                    modifier = Modifier.padding(top = dimen38()),
                    label = stringResource(R.string.username),
                    value = uiState.username,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputUsername(newValue)
                        )
                    },
                    placeholder = stringResource(R.string.place_holder),
                    helperText = stringResource(R.string.description),
                )
                LoginInputField(
                    modifier = Modifier.padding(top = dimen20()),
                    label = stringResource(R.string.password),
                    value = uiState.password,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputPassword(newValue)
                        )
                    },
                    placeholder = stringResource(R.string.place_holder),
                    helperText = stringResource(R.string.description),
                )
                LoginInputField(
                    modifier = Modifier.padding(top = dimen20()),
                    label = stringResource(R.string.name),
                    value = uiState.name,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputName(newValue)
                        )
                    },
                    placeholder = stringResource(R.string.place_holder),
                    helperText = stringResource(R.string.description),
                )
                LoginBirthButton(uiState, viewModel)
                ExtraLargeSpacer()
                RegisterButton(viewModel)
            }

            when(val commonState = uiState.commonState) {
                is CommonState.Loading -> FullScreenLoading()
                is CommonState.Idle -> navigator.openSam()
                is CommonState.Error -> {
                    ErrorDialog(content = commonState.mess) {
                        viewModel.onTriggerEvent(LoginEvent.IdleReturn)
                    }
                }
                else -> {}
            }
        }
    )
}

@Composable
private fun LoginInputField(
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

@Composable
private fun LoginBirthButton(
    uiState: LoginState,
    viewModel: LoginViewModel,
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
                    viewModel.onTriggerEvent(LoginEvent.InputBirth(year, month))
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

@Composable
private fun RegisterButton(viewModel: LoginViewModel) {
    Button(
        onClick = { viewModel.onTriggerEvent(LoginEvent.Register) },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PurpleBgColor,
            contentColor = BaseColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimen54())
            .padding(horizontal = dimen38())
            .clip(AppShapes.small)
    ) {
        Text(
            stringResource(R.string.register),
            style = AppTypography.button
        )
    }
}