package com.password.manager.ui.screens.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun BaseInputField(
    hint: String,
    parameter: Parameter,
    onValueChanged: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    var valueVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        shape = RoundedCornerShape(5.dp),
        value = value,
        onValueChange = {
            onValueChanged(it)
            value = it
        },
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = ExtendedTheme.colors.labelSecondary
                )
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ExtendedTheme.colors.labelPrimary,
            unfocusedTextColor = ExtendedTheme.colors.labelPrimary,
            focusedBorderColor = ExtendedTheme.colors.labelTertiary,
            unfocusedBorderColor = ExtendedTheme.colors.labelTertiary,
            focusedContainerColor = ExtendedTheme.colors.backSecondary,
            unfocusedContainerColor = ExtendedTheme.colors.backSecondary,
        ),
        trailingIcon = {
            if (parameter == Parameter.PASSWORD) {
                val icon = if (valueVisibility) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { valueVisibility = !valueVisibility }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Visibility icon",
                        tint = ExtendedTheme.colors.labelSecondary
                    )
                }
            }
        },
        visualTransformation = if (parameter == Parameter.PASSWORD && !valueVisibility) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}

@Preview
@Composable
private fun BaseInputFieldPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        BaseInputField(
            hint = "Password",
            parameter = Parameter.PASSWORD
        ) {}
    }
}
