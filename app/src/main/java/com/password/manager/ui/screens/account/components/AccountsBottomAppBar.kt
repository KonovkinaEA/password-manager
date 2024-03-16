package com.password.manager.ui.screens.account.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.Red
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun AccountsBottomAppBar(enable: Boolean, onDeleteButtonClick: () -> Unit) {
    BottomAppBar(
        containerColor = ExtendedTheme.colors.backSecondary,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AppBarTextButton(
                enable = enable,
                text = "Delete account",
                containerColor = Red
            ) { onDeleteButtonClick() }
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AccountsBottomAppBar(true) {}
    }
}
