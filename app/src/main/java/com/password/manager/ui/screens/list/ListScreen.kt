package com.password.manager.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.data.model.SiteAccount
import com.password.manager.ui.screens.list.components.AccountCard
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.util.accounts

@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    ListScreenContent(state)
}

@Composable
private fun ListScreenContent(state: List<SiteAccount>) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
                .background(ExtendedTheme.colors.backPrimary),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(state) {
                AccountCard(it.url, it.iconUrl)
            }
        }
    }
}

@Preview
@Composable
private fun ListScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        ListScreenContent(state = accounts)
    }
}
