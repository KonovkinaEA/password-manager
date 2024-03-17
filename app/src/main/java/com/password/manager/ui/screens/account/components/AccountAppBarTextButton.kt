package com.password.manager.ui.screens.account.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.Green
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.theme.White

@Composable
fun AppBarTextButton(enable: Boolean, text: String, containerColor: Color, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = White,
            disabledContainerColor = ExtendedTheme.colors.backSecondary,
            disabledContentColor = ExtendedTheme.colors.labelSecondary
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 5.dp),
        enabled = enable,
        border = if (!enable) {
            BorderStroke(width = 1.dp, color = ExtendedTheme.colors.labelTertiary)
        } else {
            null
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun AppBarTextButtonPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AppBarTextButton(enable = true, text = "Save", containerColor = Green) {}
    }
}
