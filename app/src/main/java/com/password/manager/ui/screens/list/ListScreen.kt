package com.password.manager.ui.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    ListScreenContent()
}

@Composable
fun ListScreenContent() {

}

@Preview
@Composable
private fun ListScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        ListScreenContent()
    }
}
