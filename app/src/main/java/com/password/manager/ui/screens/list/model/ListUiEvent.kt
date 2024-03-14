package com.password.manager.ui.screens.list.model

sealed class ListUiEvent {
    data class NavigateToEditAccount(val id: String) : ListUiEvent()
    data object NavigateToAddAccount : ListUiEvent()
}
