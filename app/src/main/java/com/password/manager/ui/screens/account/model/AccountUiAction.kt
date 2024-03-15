package com.password.manager.ui.screens.account.model

sealed class AccountUiAction {
    data class SaveAccount(val id: String) : AccountUiAction()
    data object CloseAccount : AccountUiAction()
}
