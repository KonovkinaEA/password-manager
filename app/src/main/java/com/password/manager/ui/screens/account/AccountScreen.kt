package com.password.manager.ui.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.ui.screens.account.components.AccountBaseCard
import com.password.manager.ui.screens.account.components.AccountsBottomAppBar
import com.password.manager.ui.screens.account.components.AccountsTopAppBar
import com.password.manager.ui.screens.account.components.CardContent
import com.password.manager.ui.screens.account.model.AccountUiAction
import com.password.manager.ui.screens.common.Parameter
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview

@Composable
fun AccountScreen(onScreenClose: () -> Unit, viewModel: AccountViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.closeScreen.collect { if (it) onScreenClose() }
    }

    AccountScreenContent(state, viewModel::onUiAction)
}

@Composable
private fun AccountScreenContent(state: AccountUiState, onUiAction: (AccountUiAction) -> Unit) {
    val enableSave =
        state.url.isNotEmpty() && state.login.isNotEmpty() && state.password.isNotEmpty()

    Scaffold(
        topBar = { AccountsTopAppBar(enableSave = enableSave, onUiAction) },
        bottomBar = {
            AccountsBottomAppBar(enable = !state.isNewAccount) {
                onUiAction(AccountUiAction.DeleteAccount)
            }
        },
        containerColor = ExtendedTheme.colors.backPrimary
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WebsitePart(
                state.isNewAccount,
                state.url
            ) { onUiAction(AccountUiAction.UpdateWebsite(it)) }
            Spacer(modifier = Modifier.height(15.dp))
            AccountBaseCard {
                CardContent(value = state.login, parameter = Parameter.LOGIN) {
                    onUiAction(AccountUiAction.UpdateLogin(it))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            AccountBaseCard {
                CardContent(value = state.password, parameter = Parameter.PASSWORD) {
                    onUiAction(AccountUiAction.UpdatePassword(it))
                }
            }
        }
    }
}

@Composable
private fun WebsitePart(isNewAccount: Boolean, url: String, updateWebsite: (String) -> Unit) {
    if (isNewAccount) {
        AccountBaseCard {
            CardContent(value = url, parameter = Parameter.WEBSITE) { updateWebsite(it) }
        }
    } else {
        AccountBaseCard {
            Box(modifier = Modifier.padding(15.dp)) {
                Text(
                    text = url, style = MaterialTheme.typography.titleLarge.copy(
                        color = ExtendedTheme.colors.labelPrimary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun AccountScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        AccountScreenContent(state = AccountUiState(false, "url", "login", "password")) {}
    }
}
