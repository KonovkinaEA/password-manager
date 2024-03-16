package com.password.manager.ui.screens.start.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.password.manager.ui.screens.common.BaseInputField
import com.password.manager.ui.screens.common.Parameter
import com.password.manager.ui.screens.start.model.StartUiAction
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.Green
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.Red
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.theme.White

@Composable
fun MasterPasswordCard(
    titleText: String,
    buttonText: String,
    errorText: String,
    enteredPassword: String,
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
                style = MaterialTheme.typography.bodyMedium.copy(color = Red),
                modifier = Modifier
                    .height(30.dp)
                    .wrapContentHeight()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                BaseInputField(
                    hint = Parameter.MASTER_PASSWORD.value,
                    parameter = Parameter.MASTER_PASSWORD
                ) {
                    onUiAction(StartUiAction.UpdateMasterPassword(it))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { onUiAction(StartUiAction.EnterMasterPassword(enteredPassword)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,
                        disabledContainerColor = ExtendedTheme.colors.backSecondary
                    ),
                    enabled = enteredPassword.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    border = if (enteredPassword.isEmpty()) {
                        BorderStroke(width = 1.dp, color = ExtendedTheme.colors.labelTertiary)
                    } else {
                        null
                    }
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (enteredPassword.isEmpty()) {
                                ExtendedTheme.colors.labelTertiary
                            } else {
                                White
                            }
                        )
                    )
                }
            }
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
            enteredPassword = "",
            onUiAction = {}
        )
    }
}
