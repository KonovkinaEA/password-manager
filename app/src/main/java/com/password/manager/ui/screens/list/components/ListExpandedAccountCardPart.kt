package com.password.manager.ui.screens.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun ExpandedPart(login: String, password: String, onEditClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        LoginPasswordCard(login, password)
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = ExtendedTheme.colors.supportOverlay,
                contentColor = ExtendedTheme.colors.labelSecondary
            ),
            shape = RoundedCornerShape(10.dp),
            onClick = { onEditClick() }
        ) {
            Text(
                text = "Edit",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun LoginPasswordCard(login: String, password: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.supportOverlay,
            contentColor = ExtendedTheme.colors.labelSecondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Account icon"
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                SelectionContainer {
                    Text(
                        text = login,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                SelectionContainer {
                    Text(
                        text = password,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpandedPartPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        ExpandedPart("login", "password") {}
    }
}
