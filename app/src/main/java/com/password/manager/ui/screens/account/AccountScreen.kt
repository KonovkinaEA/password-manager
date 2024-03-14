package com.password.manager.ui.screens.account

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun AccountScreen(onScreenClose: () -> Unit, viewModel: AccountViewModel = hiltViewModel()) {

}

@Composable
private fun AccountScreenContent() {

}

@Preview
@Composable
private fun AccountScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AccountScreenContent()
    }
}
