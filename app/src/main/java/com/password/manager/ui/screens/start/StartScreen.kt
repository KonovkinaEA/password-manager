package com.password.manager.ui.screens.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.ui.screens.start.components.MasterPasswordCard
import com.password.manager.ui.screens.start.model.StartUiAction
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun StartScreen(toNextScreen: () -> Unit, viewModel: StartViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.successfulEntry.collect { toNextScreen() }
    }

    StartScreenContent(state, viewModel::onUiAction)
}

@Composable
private fun StartScreenContent(state: StartScreenUiState, onUiAction: (StartUiAction) -> Unit) {
    val isMasterPasswordSet = state.isMasterPasswordSet
    val (title, buttonText) = when (isMasterPasswordSet) {
        true -> "The vault is closed.\nEnter the master password" to "Unlock"
        false -> "Enter a new master password" to "Enter"
        else -> null to null
    }
    val errorMessage =
        if (!state.isMasterPasswordCorrect) "The password is incorrect. Try again" else ""

    Scaffold(containerColor = ExtendedTheme.colors.backPrimary) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            if (title != null && buttonText != null) {
                MasterPasswordCard(title, buttonText, errorMessage, onUiAction)
            }
        }
    }
}

@Preview
@Composable
private fun StartScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        StartScreenContent(
            state = StartScreenUiState(true),
            onUiAction = {}
        )
    }
}
