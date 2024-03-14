package com.password.manager.ui.screens.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.password.manager.R
import com.password.manager.data.model.Account
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun AccountCard(state: Account) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.backSecondary
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = { expanded = !expanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
        ) {
            AsyncImage(
                model = state.iconUrl,
                contentDescription = "Site icon",
                placeholder = painterResource(id = R.drawable.baseline_cookie_24),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = state.url,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = ExtendedTheme.colors.labelPrimary
                )
            )
        }

        if (expanded) {
            LoginPasswordPart(state.login, state.password)
        }
    }
}

@Composable
private fun LoginPasswordPart(login: String, password: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .padding(bottom = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.supportOverlay,
            contentColor = ExtendedTheme.colors.labelTertiary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                tint = ExtendedTheme.colors.labelSecondary,
                contentDescription = "Account icon"
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column {
                SelectionContainer {
                    Text(
                        text = login,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = ExtendedTheme.colors.labelSecondary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                SelectionContainer {
                    Text(
                        text = password,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = ExtendedTheme.colors.labelSecondary
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AccountCardPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AccountCard(
            Account(
                url = "https://github.com/",
                iconUrl = "https://github.com/favicon.ico",
                login = "login",
                password = "password"
            )
        )
    }
}
