package com.password.manager.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.data.model.AccountData
import com.password.manager.ui.screens.list.components.AccountCard
import com.password.manager.ui.screens.list.components.ListTopAppBar
import com.password.manager.ui.screens.list.model.ListUiAction
import com.password.manager.ui.screens.list.model.ListUiEvent
import com.password.manager.ui.theme.ExtendedTheme
import com.password.manager.ui.theme.PasswordManagerTheme
import com.password.manager.ui.theme.ThemeModePreview
import com.password.manager.ui.util.accounts

@Composable
fun ListScreen(
    addAccount: () -> Unit,
    editAccount: (String) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                ListUiEvent.NavigateToAddAccount -> addAccount()
                is ListUiEvent.NavigateToEditAccount -> editAccount(it.id)
            }
        }
    }

    ListScreenContent(state, viewModel::onUiAction)
}

@Composable
private fun ListScreenContent(state: List<AccountData>, onUiAction: (ListUiAction) -> Unit) {
    Scaffold(
        topBar = { ListTopAppBar(onUiAction) },
        containerColor = ExtendedTheme.colors.backPrimary
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Spacer(modifier = Modifier.height(5.dp)) }
            items(state) {
                AccountCard(state = it, onEditClick = {
                    onUiAction(ListUiAction.EditAccount(it.id))
                })
            }
            item { Spacer(modifier = Modifier.height(5.dp)) }
        }
    }
}

@Preview
@Composable
private fun ListScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    PasswordManagerTheme(darkTheme = darkTheme) {
        ListScreenContent(state = accounts, onUiAction = {})
    }
}
