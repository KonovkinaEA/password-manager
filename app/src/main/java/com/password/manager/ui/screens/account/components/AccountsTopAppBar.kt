package com.password.manager.ui.screens.account.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.screens.account.model.AccountUiAction
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.Green
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsTopAppBar(id: String, onUiAction: (AccountUiAction) -> Unit) {
    TopAppBar(
        modifier = Modifier.shadow(10.dp),
        title = {},
        navigationIcon = {
            IconButton(onClick = { onUiAction(AccountUiAction.CloseAccount) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to the List screen button icon",
                    tint = ExtendedTheme.colors.labelPrimary
                )
            }
        },
        actions = {
            TextButton(
                onClick = { onUiAction(AccountUiAction.SaveAccount) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    contentColor = White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ExtendedTheme.colors.backSecondary
        )
    )
}

@Preview
@Composable
private fun TopAppBarPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AccountsTopAppBar(id = "id", onUiAction = {})
    }
}
