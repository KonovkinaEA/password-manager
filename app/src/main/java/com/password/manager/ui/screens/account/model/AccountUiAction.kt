package com.password.manager.ui.screens.account.model

sealed class AccountUiAction {
    data class UpdateWebsite(val website: String) : AccountUiAction()
    data class UpdateLogin(val login: String) : AccountUiAction()
    data class UpdatePassword(val password: String) : AccountUiAction()
    data object SaveAccount : AccountUiAction()
    data object CloseAccount : AccountUiAction()
}
