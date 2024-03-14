package com.password.manager.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.Green
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.theme.White

@Composable
fun PasswordField(hint: String, buttonText: String, onButtonClick: (String) -> Unit) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(5.dp),
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = ExtendedTheme.colors.labelSecondary
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedContainerColor = ExtendedTheme.colors.backSecondary,
                unfocusedContainerColor = ExtendedTheme.colors.backSecondary
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Visibility icon",
                        tint = ExtendedTheme.colors.labelSecondary
                    )
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onButtonClick(password) },
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Green,
                disabledContainerColor = ExtendedTheme.colors.backSecondary
            ),
            enabled = password.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            border = if (password.isEmpty()) {
                BorderStroke(width = 1.dp, color = ExtendedTheme.colors.labelTertiary)
            } else {
                null
            }
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (password.isEmpty()) ExtendedTheme.colors.labelTertiary else White
                )
            )
        }
    }
}

@Preview
@Composable
private fun PasswordFieldPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        PasswordField(hint = "Password", buttonText = "Unlock", onButtonClick = {})
    }
}
