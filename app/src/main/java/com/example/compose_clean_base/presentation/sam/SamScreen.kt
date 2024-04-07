package com.example.compose_clean_base.presentation.sam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_clean_base.R
import com.example.compose_clean_base.app.component.Toolbar
import com.example.compose_clean_base.app.theme.dimen24
import com.example.compose_clean_base.app.theme.dimen40
import com.example.compose_clean_base.provider.mask.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.asStateFlow

@Destination
@Composable
fun SamScreen(
    viewModel: SamViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    Scaffold(
        topBar = { Toolbar(R.string.sam_nav_tab) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = dimen24()
                    )
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimen40())
            ) {
            }
        }
    )
}
