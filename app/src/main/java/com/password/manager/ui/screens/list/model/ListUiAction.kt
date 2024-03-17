package com.password.manager.ui.screens.list.model

sealed class ListUiAction {
    data class EditAccount(val id: String) : ListUiAction()
    data object CreateAccount : ListUiAction()
    data object ClearCache : ListUiAction()
}
