package com.password.manager.ui.screens.account.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.screens.common.BaseInputField
import com.password.manager.ui.screens.common.Parameter
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun CardContent(
    value: String,
    parameter: Parameter,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = parameter.value, style = MaterialTheme.typography.titleLarge.copy(
                color = ExtendedTheme.colors.labelPrimary
            )
        )
        BaseInputField(
            hint = value,
            parameter = parameter,
            onValueChanged = onValueChanged
        )
    }
}

@Preview
@Composable
private fun CardContentPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        CardContent(
            value = "",
            parameter = Parameter.PASSWORD
        ) {}
    }
}
