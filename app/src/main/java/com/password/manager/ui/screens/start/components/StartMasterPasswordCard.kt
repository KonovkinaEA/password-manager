package com.password.manager.ui.screens.start.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.screens.common.PasswordField
import com.password.manager.ui.screens.start.model.StartUiAction
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.Red
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun MasterPasswordCard(
    titleText: String,
    buttonText: String,
    errorText: String,
    onUiAction: (StartUiAction) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.backSecondary
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = titleText,
                style = MaterialTheme.typography.displayLarge.copy(
                    color = ExtendedTheme.colors.labelPrimary
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = errorText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(color = Red),
                modifier = Modifier
                    .height(30.dp)
                    .wrapContentHeight()
            )
            PasswordField(hint = "Master password", buttonText = buttonText, onButtonClick = {
                onUiAction(StartUiAction.EnterMasterPassword(it))
            })
        }
    }
}

@Preview
@Composable
private fun EnterMasterPasswordPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        MasterPasswordCard(
            titleText = "The vault is closed.\nEnter the master password",
            errorText = "The password is incorrect. Try again",
            buttonText = "Unlock",
            onUiAction = {}
        )
    }
}
